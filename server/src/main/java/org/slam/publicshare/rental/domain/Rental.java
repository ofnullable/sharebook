package org.slam.publicshare.rental.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(nullable = false, updatable = false)
    private Long bookId;

    @Column(nullable = false, updatable = false)
    private Long accountId;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    @OneToMany(mappedBy = "rental", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<RentalHistory> histories = new ArrayList<>();

    @Builder
    public Rental(Long bookId, Long accountId) {
        this.bookId = bookId;
        this.accountId = accountId;
    }

    public void rental() {
        this.histories.add(buildRentHistory(RentalStatus.REQUESTED));
    }

    public void accept() {
        this.startedAt = LocalDateTime.now();
        this.histories.add(buildRentHistory(RentalStatus.ACCEPTED));
    }

    public void reject() {
        this.endedAt = LocalDateTime.now();
        this.histories.add(buildRentHistory(RentalStatus.REJECTED));
    }

    public void returned() {
        this.endedAt = LocalDateTime.now();
        this.histories.add(buildRentHistory(RentalStatus.RETURNED));
    }

    private RentalHistory buildRentHistory(RentalStatus status) {
        return RentalHistory.builder()
                .rental(this)
                .status(status)
                .build();
    }

}
