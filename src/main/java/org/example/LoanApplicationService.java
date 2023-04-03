package org.example;

import java.time.LocalTime;

public class LoanApplicationService implements LoanAppInterface{
    @Override
    public boolean apply_for_loan(long term, double amount) {

        if (term >= MIN_TERM && term <= MAX_TERM) {
            if (amount >= MIN_AMOUNT && amount <= MAX_AMOUNT) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean request_time_check(LocalTime time, double amount) {

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
    public double cost_of_loan(double amount) {

            return amount * COST_RATE;
    }

    @Override
    public long extend_due_date(long term) {

        long newTerm = term + EXTENSION;
        if (newTerm > MAX_TERM) {
            newTerm = MAX_TERM;
        }
        return newTerm;
    }
}
