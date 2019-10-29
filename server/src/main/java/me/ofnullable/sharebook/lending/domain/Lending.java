package me.ofnullable.sharebook.lending.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ofnullable.sharebook.book.domain.Book;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lending {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private Book book;

    @Column(nullable = false, updatable = false)
    private Long borrowerId;

    @Enumerated(EnumType.STRING)
    private LendingStatus currentStatus;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    @OneToMany(mappedBy = "lending", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LendingHistory> histories = new ArrayList<>();

    @Builder
    public Lending(Book book, Long accountId) {
        this.book = book;
        this.borrowerId = accountId;
    }

    public void borrow() {
        this.currentStatus = LendingStatus.REQUESTED;
        this.histories.add(buildLendingHistory(LendingStatus.REQUESTED));

        this.book.addLending(this);
        this.book.toUnavailable(this.borrowerId);
    }

    public void canceled() {
        this.currentStatus = LendingStatus.CANCELED;
        this.histories.add(buildLendingHistory(LendingStatus.CANCELED));

        this.book.toAvailable();
    }

    public void accept() {
        this.startedAt = LocalDateTime.now();
        this.currentStatus = LendingStatus.ACCEPTED;
        this.histories.add(buildLendingHistory(LendingStatus.ACCEPTED));
    }

    public void reject() {
        this.endedAt = LocalDateTime.now();
        this.currentStatus = LendingStatus.REJECTED;
        this.histories.add(buildLendingHistory(LendingStatus.REJECTED));

        this.book.toAvailable();
    }

    public void returned() {
        this.endedAt = LocalDateTime.now();
        this.currentStatus = LendingStatus.RETURNED;
        this.histories.add(buildLendingHistory(LendingStatus.RETURNED));

        this.book.toAvailable();
    }

    private LendingHistory buildLendingHistory(LendingStatus status) {
        return LendingHistory.builder()
                .lending(this)
                .status(status)
                .build();
    }

}
