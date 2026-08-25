package lk.ijse.eca.borrowingservice.controller;

import jakarta.validation.Valid;
import lk.ijse.eca.borrowingservice.dto.BorrowingRequestDTO;
import lk.ijse.eca.borrowingservice.dto.BorrowingResponseDTO;
import lk.ijse.eca.borrowingservice.service.BorrowingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/borrowings")
@RequiredArgsConstructor
@Slf4j
public class BorrowingController {

    private final BorrowingService borrowingService;

    @PostMapping
    public ResponseEntity<BorrowingResponseDTO> createBorrowing(@Valid @RequestBody BorrowingRequestDTO dto) {
        log.info("POST /api/v1/borrowings - Book ISBN: {}, Member ID: {}", dto.getBookIsbn(), dto.getMemberId());
        BorrowingResponseDTO response = borrowingService.createBorrowing(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{borrowingId}")
    public ResponseEntity<BorrowingResponseDTO> updateBorrowing(
            @PathVariable Long borrowingId, @Valid @RequestBody BorrowingRequestDTO dto) {
        log.info("PUT /api/v1/borrowings/{}", borrowingId);
        BorrowingResponseDTO response = borrowingService.updateBorrowing(borrowingId, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{borrowingId}")
    public ResponseEntity<Void> deleteBorrowing(@PathVariable Long borrowingId) {
        log.info("DELETE /api/v1/borrowings/{}", borrowingId);
        borrowingService.deleteBorrowing(borrowingId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{borrowingId}")
    public ResponseEntity<BorrowingResponseDTO> getBorrowing(@PathVariable Long borrowingId) {
        log.info("GET /api/v1/borrowings/{}", borrowingId);
        BorrowingResponseDTO response = borrowingService.getBorrowing(borrowingId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<BorrowingResponseDTO>> getAllBorrowings() {
        log.info("GET /api/v1/borrowings");
        List<BorrowingResponseDTO> borrowings = borrowingService.getAllBorrowings();
        return ResponseEntity.ok(borrowings);
    }
}
