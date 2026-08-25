package lk.ijse.eca.borrowingservice.service.impl;

import lk.ijse.eca.borrowingservice.dto.BorrowingRequestDTO;
import lk.ijse.eca.borrowingservice.dto.BorrowingResponseDTO;
import lk.ijse.eca.borrowingservice.entity.Borrowing;
import lk.ijse.eca.borrowingservice.exception.BorrowingNotFoundException;
import lk.ijse.eca.borrowingservice.exception.DuplicateBorrowingException;
import lk.ijse.eca.borrowingservice.mapper.BorrowingMapper;
import lk.ijse.eca.borrowingservice.repository.BorrowingRepository;
import lk.ijse.eca.borrowingservice.service.BorrowingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BorrowingServiceImpl implements BorrowingService {

    private final BorrowingRepository borrowingRepository;
    private final BorrowingMapper borrowingMapper;

    @Override
    public BorrowingResponseDTO createBorrowing(BorrowingRequestDTO dto) {
        log.debug("Creating borrowing for book ISBN: {} and member ID: {}", dto.getBookIsbn(), dto.getMemberId());

        if (borrowingRepository.existsByBookIsbnAndMemberId(dto.getBookIsbn(), dto.getMemberId())) {
            log.warn("Duplicate borrowing detected for book ISBN: {} and member ID: {}", dto.getBookIsbn(), dto.getMemberId());
            throw new DuplicateBorrowingException(dto.getBookIsbn(), dto.getMemberId());
        }

        Borrowing borrowing = borrowingMapper.toEntity(dto);
        borrowing.setStatus("BORROWED");
        borrowing = borrowingRepository.save(borrowing);
        log.info("Borrowing created with ID: {}", borrowing.getBorrowingId());
        return borrowingMapper.toResponseDto(borrowing);
    }

    @Override
    public BorrowingResponseDTO updateBorrowing(Long borrowingId, BorrowingRequestDTO dto) {
        log.debug("Updating borrowing with ID: {}", borrowingId);

        Borrowing existing = borrowingRepository.findById(borrowingId)
                .orElseThrow(() -> {
                    log.warn("Borrowing not found for update: {}", borrowingId);
                    return new BorrowingNotFoundException(borrowingId);
                });

        borrowingMapper.updateEntity(dto, existing);
        // If returnDate is set, update status to RETURNED; otherwise keep BORROWED
        if (dto.getReturnDate() != null) {
            existing.setStatus("RETURNED");
        } else {
            existing.setStatus("BORROWED");
        }
        Borrowing updated = borrowingRepository.save(existing);
        log.info("Borrowing updated with ID: {}", borrowingId);
        return borrowingMapper.toResponseDto(updated);
    }

    @Override
    public void deleteBorrowing(Long borrowingId) {
        log.debug("Deleting borrowing with ID: {}", borrowingId);

        if (!borrowingRepository.existsById(borrowingId)) {
            log.warn("Borrowing not found for deletion: {}", borrowingId);
            throw new BorrowingNotFoundException(borrowingId);
        }

        borrowingRepository.deleteById(borrowingId);
        log.info("Borrowing deleted with ID: {}", borrowingId);
    }

    @Override
    public BorrowingResponseDTO getBorrowing(Long borrowingId) {
        log.debug("Fetching borrowing with ID: {}", borrowingId);
        Borrowing borrowing = borrowingRepository.findById(borrowingId)
                .orElseThrow(() -> {
                    log.warn("Borrowing not found: {}", borrowingId);
                    return new BorrowingNotFoundException(borrowingId);
                });
        return borrowingMapper.toResponseDto(borrowing);
    }

    @Override
    public List<BorrowingResponseDTO> getAllBorrowings() {
        log.debug("Fetching all borrowings");
        List<BorrowingResponseDTO> borrowings = borrowingRepository.findAll()
                .stream()
                .map(borrowingMapper::toResponseDto)
                .collect(Collectors.toList());
        log.debug("Fetched {} borrowings", borrowings.size());
        return borrowings;
    }
}
