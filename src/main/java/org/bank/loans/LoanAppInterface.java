package org.bank.loans;

import java.time.LocalTime;

public interface LoanAppInterface {

    final double MAX_AMOUNT = 80000.0;
    final double MIN_AMOUNT = 1000.0;
    final int MAX_TERM = 120;
    final int MIN_TERM = 21;
    final int EXTENSION = 7;
    final double COST_RATE = 0.1;

    boolean apply_for_loan(long term, double amount);
    boolean request_time_check(LocalTime time, double amount);
    double cost_of_loan(double amount);
    long extend_due_date(long term);
}
