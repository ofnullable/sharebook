package org.slam.publicshare.rental.service;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.rental.domain.Rental;
import org.slam.publicshare.rental.exception.NoSuchRentalException;
import org.slam.publicshare.rental.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalFindService {

    private final RentalRepository rentalRepository;

    public List<Rental> findByAccountId(Long accountId) {
        return rentalRepository.findAllByAccountId(accountId);
    }

    public List<Rental> findByBookId(Long bookId) {
        return rentalRepository.findAllByBookIdOrderByIdDesc(bookId);
    }

    public Rental findById(Long rentalId) {
        return rentalRepository.findById(rentalId)
                .orElseThrow(() -> new NoSuchRentalException(rentalId));
    }

}
