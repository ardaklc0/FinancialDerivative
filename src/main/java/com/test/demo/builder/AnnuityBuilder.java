package com.test.demo.builder;

import com.test.demo.model.Annuity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//To build entity with same pre-installed values we use builder pattern!
public class AnnuityBuilder {
    @Column(name = "borrowed_loan", nullable = true)
    private Double borrowedLoan;
    @Column(name = "wanna_save", nullable = true)
    private Double wannaSave;

    public AnnuityBuilder setBorrowedLoan(Double borrowedLoan) {
        this.borrowedLoan = borrowedLoan;
        return this;
    }

    public AnnuityBuilder setWannaSave(Double wannaSave) {
        this.wannaSave = wannaSave;
        return this;
    }

    public Annuity build() {
        return new Annuity(borrowedLoan, wannaSave);
    }
}
