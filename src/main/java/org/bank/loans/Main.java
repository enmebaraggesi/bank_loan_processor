package org.bank.loans;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        FakeDatabase fakeDatabase = new FakeDatabase();
        Map<Customer, ApplicationData> database = fakeDatabase.getListOfApplies();
        ApplicationProcessor processor = new ApplicationProcessor();

        for (Map.Entry<Customer, ApplicationData> entry : database.entrySet()) {

            System.out.println(processor.makeDecision(entry));
        }
    }
}