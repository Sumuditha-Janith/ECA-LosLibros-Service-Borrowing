package lk.ijse.eca.borrowingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class BorrowingResponseDTO {

    private Long borrowingId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String bookIsbn;
    private String memberId;
    private String status; // BORROWED, RETURNED
}
