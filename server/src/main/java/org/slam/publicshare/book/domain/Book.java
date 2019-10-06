package org.slam.publicshare.book.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slam.publicshare.account.domain.Account;
import org.slam.publicshare.book.domain.converter.BookStatusConverter;
import org.slam.publicshare.common.domain.Auditable;
import org.slam.publicshare.rental.domain.Rental;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends Auditable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String publisher;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Category category;

    private String description;

    @Convert(converter = BookStatusConverter.class)
    private BookStatus status;

    @ManyToOne(optional = false)
    private Account owner;

    @Column(nullable = false)
    private String imageUrl;

    private Long currentRenterId;

    @OneToMany(mappedBy = "book")
    private List<Rental> rentals = new ArrayList<>();

    @Builder
    public Book(String title, String author, String publisher, Category category, String description, Account owner, String imageUrl) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        this.description = description;
        this.owner = owner;
        this.imageUrl = imageUrl;
        this.status = BookStatus.AVAILABLE;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void addRental(Rental rental) {
        this.rentals.add(rental);
    }

    public void toUnavailable(Long renterId) {
        this.status = BookStatus.UNAVAILABLE;
        this.currentRenterId = renterId;
    }

    public void toAvailable() {
        this.status = BookStatus.AVAILABLE;
        this.currentRenterId = null;
    }

    public boolean isAvailable() {
        return this.status == BookStatus.AVAILABLE;
    }

}
