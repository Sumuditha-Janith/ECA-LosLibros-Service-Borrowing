package lk.ijse.eca.borrowingservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BorrowingRequestDTO {

    @NotNull(message = "Borrow date is required")
    private LocalDate borrowDate;

    @NotNull(message = "Due date is required")
    private LocalDate dueDate;

    private LocalDate returnDate;

    @NotBlank(message = "Book ISBN is required")
    private String bookIsbn;

    @NotBlank(message = "Member ID is required")
    private String memberId;
}
