package org.example;

import java.time.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        FakeDatabase fakeDatabase = new FakeDatabase();
        Map<Customer, ApplicationData> database = fakeDatabase.getListOfApplies();
        LoanApplicationService service = new LoanApplicationService();

        for (Map.Entry<Customer, ApplicationData> entry : database.entrySet()) {

            String fullName = entry.getKey().getFullName();
            long term = entry.getValue().getTerm();
            double amount = entry.getValue().getAmount();
            double loanCost = service.cost_of_loan(entry.getValue().getAmount());
            LocalTime time = entry.getValue().getTime();
            LocalDate dueDate = LocalDate.now().plusDays(term);
            boolean isExtended = entry.getValue().isExtendedTerm();
            boolean isApplyValid = service.apply_for_loan(term, amount);
            boolean isTimeValid = service.request_time_check(time, amount);


            if (isApplyValid) {

                if (!isTimeValid) {
                    System.out.println("Application for loan, sent by " + fullName.toUpperCase() + " is REJECTED.\n"
                            + "Time of request for max amount is invalid. INFORMATION SENT TO CUSTOMER.");
                    System.out.println();
                    continue;
                }

                if (isExtended) {
                    term = service.extend_due_date(term);
                    dueDate = dueDate.plusDays(term);
                }

                System.out.println("Application for loan, sent by " + fullName.toUpperCase() + " is ACCEPTED.\n"
                        + "Requested amount is: " + amount + "\n"
                        + "Total cost of loan is: " + loanCost + "\n"
                        + "Requested due time is: " + time + "\n"
                        + "Loan repayment date is: " + dueDate);
                System.out.println();
                continue;
            }
            System.out.println("Application for loan, sent by " + fullName.toUpperCase() + " is REJECTED.\n"
                    + "Requested values are not applicable.");
            System.out.println();
        }
    }
}