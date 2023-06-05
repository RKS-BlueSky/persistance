package com.Library.Library.repository;

import com.Library.Library.Models.Book;
import com.Library.Library.Models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    long countByDateLoanedBetween(LocalDate startDate, LocalDate endDate);

    Map<Book,Long> countByBook();

    List<Loan> findByDateReturned();
}

