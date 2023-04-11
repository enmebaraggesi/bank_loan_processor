package org.bank.loans;

import java.time.LocalTime;

public class LoanApplicationService implements LoanAppInterface{
    @Override
    public boolean applyForLoan(long term, double amount) {

        if (term >= MIN_TERM && term <= MAX_TERM) {
            if (amount >= MIN_AMOUNT && amount <= MAX_AMOUNT) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean requestTimeCheck(LocalTime time, double amount) {

        if (time.isBefore(LocalTime.of(6, 0))
                && time.isAfter(LocalTime.of(0, 0))) {
            if (amount < MAX_AMOUNT) {
                return true;
            }
            return false;
        }
        return true;
    }

    @Override
    public double costOfLoan(double amount) {

            return amount * COST_RATE;
    }

    @Override
    public long extendDueDate(long term) {

        long newTerm = term + EXTENSION;
        if (newTerm > MAX_TERM) {
            newTerm = MAX_TERM;
        }
        return newTerm;
    }
}
