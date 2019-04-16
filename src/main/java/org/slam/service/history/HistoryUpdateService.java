package org.slam.service.history;

import lombok.AllArgsConstructor;
import org.slam.dto.book.Book;
import org.slam.dto.book.BookStatus;
import org.slam.mapper.history.HistorySaveMapper;
import org.slam.mapper.history.HistoryUpdateMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.slam.dto.book.BookStatus.ON_RESERVED;
import static org.slam.utils.Builders.buildHistory;

@Service
@Transactional
@AllArgsConstructor
public class HistoryUpdateService {

    private final HistorySaveMapper historySaveMapper;
    private final HistoryUpdateMapper historyUpdateMapper;

    public int reservationRequest(Long id, String modifier) {
        return historySaveMapper.insertHistory(buildHistory(id, ON_RESERVED, modifier));
    }

    public int cancelReservationRequest(Long id, String username) {
        return historyUpdateMapper.cancelReservation(id, username);
    }

    public int returnRequest(Long id, String modifier) {
        return historyUpdateMapper.updateBookHistoryToReturnRequest(Book.builder().id(id).status(BookStatus.RETURN_REQUEST).modifiedBy(modifier).build());
    }

}
