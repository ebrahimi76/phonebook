package ir.ebrahimi.phonebook.repository;

import ir.ebrahimi.phonebook.model.PhoneBookRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PhoneBookRowRepository extends JpaRepository<PhoneBookRow, Long> {
}
