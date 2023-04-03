package org.example;

import java.time.LocalTime;

public class ApplicationData {

    private long term;
    private double amount;
    private LocalTime time;
    private boolean extendedTerm;

    public ApplicationData(int term, double amount, LocalTime time, boolean extendedTerm) {
        this.term = term;
        this.amount = amount;
        this.time = time;
        this.extendedTerm = extendedTerm;
    }

    public long getTerm() {
        return term;
    }

    public double getAmount() {
        return amount;
    }

    public LocalTime getTime() {
        return time;
    }

    public boolean isExtendedTerm() {
        return extendedTerm;
    }
}
