package lk.ijse.eca.borrowingservice.exception;

public class BorrowingNotFoundException extends RuntimeException {

    public BorrowingNotFoundException(Long borrowingId) {
        super("Borrowing record with ID '" + borrowingId + "' not found");
    }
}