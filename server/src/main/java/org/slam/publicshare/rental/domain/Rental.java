package org.slam.publicshare.rental.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slam.publicshare.book.domain.Book;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rental {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private Book book;

    @Column(nullable = false, updatable = false)
    private Long accountId;

    @Enumerated(EnumType.STRING)
    private RentalStatus currentStatus;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    @OneToMany(mappedBy = "rental", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RentalHistory> histories = new ArrayList<>();

    @Builder
    public Rental(Book book, Long accountId) {
        this.book = book;
        this.accountId = accountId;
    }

    public void request() {
        this.currentStatus = RentalStatus.REQUESTED;
        this.histories.add(buildRentalHistory(RentalStatus.REQUESTED));

        this.book.addRental(this);
        this.book.toUnavailable(this.accountId);
    }

    public void accept() {
        this.startedAt = LocalDateTime.now();
        this.currentStatus = RentalStatus.ACCEPTED;
        this.histories.add(buildRentalHistory(RentalStatus.ACCEPTED));
    }

    public void reject() {
        this.endedAt = LocalDateTime.now();
        this.currentStatus = RentalStatus.REJECTED;
        this.histories.add(buildRentalHistory(RentalStatus.REJECTED));

        this.book.toAvailable();
    }

    public void returned() {
        this.endedAt = LocalDateTime.now();
        this.currentStatus = RentalStatus.RETURNED;
        this.histories.add(buildRentalHistory(RentalStatus.RETURNED));

        this.book.toAvailable();
    }

    private RentalHistory buildRentalHistory(RentalStatus status) {
        return RentalHistory.builder()
                .rental(this)
                .status(status)
                .build();
    }

}
