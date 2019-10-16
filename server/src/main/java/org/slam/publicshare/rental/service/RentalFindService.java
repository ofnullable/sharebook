package org.slam.publicshare.rental.service;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.common.dto.PageRequest;
import org.slam.publicshare.rental.domain.Rental;
import org.slam.publicshare.rental.domain.RentalStatus;
import org.slam.publicshare.rental.exception.NoSuchRentalException;
import org.slam.publicshare.rental.repository.RentalRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalFindService {

    private final RentalRepository rentalRepository;

    public Page<Rental> findAllByAccountIdAndCurrentStatus(Long accountId, RentalStatus rentalStatus, PageRequest pageRequest) {
        return rentalRepository.findAllByAccountIdAndCurrentStatus(accountId, rentalStatus, pageRequest.of());
    }

    public Rental findById(Long rentalId) {
        return rentalRepository.findById(rentalId)
                .orElseThrow(() -> new NoSuchRentalException(rentalId));
    }

}
