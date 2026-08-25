package lk.ijse.eca.borrowingservice.service;

import lk.ijse.eca.borrowingservice.dto.BorrowingRequestDTO;
import lk.ijse.eca.borrowingservice.dto.BorrowingResponseDTO;

import java.util.List;

public interface BorrowingService {

    BorrowingResponseDTO createBorrowing(BorrowingRequestDTO dto);

    BorrowingResponseDTO updateBorrowing(Long borrowingId, BorrowingRequestDTO dto);

    void deleteBorrowing(Long borrowingId);

    BorrowingResponseDTO getBorrowing(Long borrowingId);

    List<BorrowingResponseDTO> getAllBorrowings();
}
