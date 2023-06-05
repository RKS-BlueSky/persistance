package com.Library.Library.controller;

import com.Library.Library.Models.Book;
import com.Library.Library.Models.Loan;
import com.Library.Library.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/loans")
public class LoanController {
    private final LoanRepository loanRepository;

    @Autowired
    public LoanController(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @GetMapping("/{id}")
    public Loan getLoanById(@PathVariable Long id) {
        try {
            return loanRepository.findById(id)
                    .orElseThrow(ChangeSetPersister.NotFoundException::new);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public Loan createLoan(@RequestBody Loan loan) {
        return loanRepository.save(loan);
    }

    @PutMapping("/{id}")
    public Loan updateLoan(@PathVariable Long id, @RequestBody Loan updatedLoan) {
        Loan loan = null;
        try {
            loan = loanRepository.findById(id)
                    .orElseThrow(ChangeSetPersister.NotFoundException::new);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }

        loan.setDateLoaned(updatedLoan.getDateLoaned());
        loan.setDateEnded(updatedLoan.getDateEnded());
        loan.setDateReturned(updatedLoan.getDateReturned());
        loan.setMember(updatedLoan.getMember());
        loan.setBooks(updatedLoan.getBooks());

        return loanRepository.save(loan);
    }

    @GetMapping("/loans/count")
    public long getNumberOfLoans() {
        return loanRepository.count();
    }

    @GetMapping("/loans/countByDateRange")
    public long getNumberOfLoansWithinDateRange(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return loanRepository.countByDateLoanedBetween(startDate, endDate);
    }

    @GetMapping("/loans/countPerBook")
    public int getNumberOfLoansPerBook() {
        return loanRepository.countDistinctBooksInLoans();
    }

    @GetMapping("/loans/ongoing")
    public List<Loan> getOngoingLoans() {
        return loanRepository.findByDateReturnedIsNull();
    }

    @DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable Long id) {
        loanRepository.deleteById(id);
    }
}

