package org.bank.loans;

import java.time.*;
import java.util.Map;

public class ApplicationProcessor {

    LoanApplicationService service = new LoanApplicationService();

    //main method to construct final decision text
    public String makeDecision(Map.Entry<Customer, ApplicationData> entry) {

        //fields for extension check
        long term = entry.getValue().getTerm();
        LocalDate dueDate = LocalDate.now();
        boolean isExtended = entry.getValue().isExtendedTerm();

        //extension check
        if (isExtended) {
            term = service.extendDueDate(term);
            dueDate = dueDate.plusDays(term);
        } else {
            dueDate = dueDate.plusDays(term);
        }

        //fields for decision check
        String fullName = entry.getKey().getFullName();
        LocalTime time = entry.getValue().getTime();
        double amount = entry.getValue().getAmount();
        boolean isApplyValid = service.applyForLoan(term, amount);
        boolean isTimeValid = service.requestTimeCheck(time, amount);

        //decision check
        String decisionText = "Application for loan, sent by " + fullName.toUpperCase() +
                validationProcess(isApplyValid, isTimeValid);

        //field for positive decision summary
        double loanCost = service.costOfLoan(entry.getValue().getAmount());

        //positive decision summary
        if (decisionText.contains("ACCEPTED")) {
            decisionText += "Requested amount is: " + amount + "\n"
                    + "Total cost of loan is: " + loanCost + "\n"
                    + "Requested due time is: " + term + " days\n"
                    + "Loan repayment date is: " + dueDate + "\n";
        }
        //to be displayed
        return decisionText;
    }

    //supply method to create rejection/acceptation text
    public String validationProcess(boolean isApplyValid, boolean isTimeValid) {

        //field to be returned
        String validation;

        //decision-making
        if (isApplyValid) {
            if (!isTimeValid) {
                validation = " is REJECTED.\n"
                        + "Time of request for max amount is invalid. INFORMATION SENT TO CUSTOMER.\n";
                return validation;
            }
            validation = " is ACCEPTED.\n";
            return validation;
        }
        validation = " is REJECTED.\n"
                + "Requested values are not applicable.\n";
        return validation;
    }
}
