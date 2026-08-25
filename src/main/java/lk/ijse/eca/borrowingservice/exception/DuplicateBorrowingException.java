package lk.ijse.eca.borrowingservice.exception;

public class DuplicateBorrowingException extends RuntimeException {

    public DuplicateBorrowingException(String bookIsbn, String memberId) {
        super("Borrowing already exists for book ISBN '" + bookIsbn + "' and member ID '" + memberId + "'");
    }
}