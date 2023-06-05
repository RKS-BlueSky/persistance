package com.Library.Library.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateLoaned;
    private LocalDate dateEnded;
    private LocalDate dateReturned;
    @ManyToOne
    private Member member;
    @ManyToMany
    private List<Book> books;

    public Loan() {
    }

    public Loan(Long id, LocalDate dateLoaned, LocalDate dateEnded, LocalDate dateReturned, Member member, List<Book> books) {
        this.id = id;
        this.dateLoaned = dateLoaned;
        this.dateEnded = dateEnded;
        this.dateReturned = dateReturned;
        this.member = member;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateLoaned() {
        return dateLoaned;
    }

    public void setDateLoaned(LocalDate dateLoaned) {
        this.dateLoaned = dateLoaned;
    }

    public LocalDate getDateEnded() {
        return dateEnded;
    }

    public void setDateEnded(LocalDate dateEnded) {
        this.dateEnded = dateEnded;
    }

    public LocalDate getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(LocalDate dateReturned) {
        this.dateReturned = dateReturned;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

