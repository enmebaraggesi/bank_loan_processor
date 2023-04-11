package org.bank.loans;

import java.time.LocalTime;

public interface LoanAppInterface {

    final double MAX_AMOUNT = 80000.0;
    final double MIN_AMOUNT = 1000.0;
    final int MAX_TERM = 120;
    final int MIN_TERM = 21;
    final int EXTENSION = 7;
    final double COST_RATE = 0.1;

    boolean applyForLoan(long term, double amount);
    boolean requestTimeCheck(LocalTime time, double amount);
    double costOfLoan(double amount);
    long extendDueDate(long term);
}
