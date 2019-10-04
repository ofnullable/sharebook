package org.slam.publicshare.rental.domain;

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

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private Book book;

    @Column(nullable = false, updatable = false)
    private Long accountId;

    @Enumerated(EnumType.STRING)
    private RentalStatus currentStatus;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    @OneToMany(mappedBy = "rental", cascade = CascadeType.ALL)
    private List<RentalHistory> histories = new ArrayList<>();

    @Builder
    public Rental(Book book, Long accountId) {
        this.book = book;
        this.accountId = accountId;
    }

    public void rental() {
        this.currentStatus = RentalStatus.REQUESTED;
        this.histories.add(buildRentHistory(RentalStatus.REQUESTED));

        this.book.addRental(this);
        this.book.changeToUnavailable();
    }

    public void accept() {
        this.startedAt = LocalDateTime.now();
        this.currentStatus = RentalStatus.ACCEPTED;
        this.histories.add(buildRentHistory(RentalStatus.ACCEPTED));
    }

    public void reject() {
        this.endedAt = LocalDateTime.now();
        this.currentStatus = RentalStatus.REJECTED;
        this.histories.add(buildRentHistory(RentalStatus.REJECTED));

        this.book.changeToAvailable();
    }

    public void returned() {
        this.endedAt = LocalDateTime.now();
        this.currentStatus = RentalStatus.RETURNED;
        this.histories.add(buildRentHistory(RentalStatus.RETURNED));

        this.book.changeToAvailable();
    }

    private RentalHistory buildRentHistory(RentalStatus status) {
        return RentalHistory.builder()
                .rental(this)
                .status(status)
                .build();
    }

}
