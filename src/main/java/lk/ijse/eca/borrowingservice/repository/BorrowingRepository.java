package lk.ijse.eca.borrowingservice.repository;

import lk.ijse.eca.borrowingservice.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

    boolean existsByBookIsbnAndMemberId(String bookIsbn, String memberId);
}
