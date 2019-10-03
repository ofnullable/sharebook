package org.slam.publicshare.rental.service;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.book.service.BookFindService;
import org.slam.publicshare.rental.domain.Rental;
import org.slam.publicshare.rental.repository.RentalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RentalSaveService {

    private final RentalValidator validator;
    private final BookFindService bookFindService;
    private final RentalRepository rentalRepository;

    @Transactional
    public Rental rentalRequest(Long bookId, Long accountId) {
        var book = bookFindService.findById(bookId);
        var rental = Rental.builder().book(book).accountId(accountId).build();
        validator.validate(rental);
        rental.rental();
        return rentalRepository.save(rental);
    }

}
