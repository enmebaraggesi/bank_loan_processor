package org.bank.loans;

import java.time.*;
import java.util.Map;

public class ApplicationProcessor {

    LoanApplicationService service = new LoanApplicationService();

    public String makeDecision(Map.Entry<Customer, ApplicationData> entry) {

        String fullName = entry.getKey().getFullName();
        long term = entry.getValue().getTerm();
        double amount = entry.getValue().getAmount();
        double loanCost = service.cost_of_loan(entry.getValue().getAmount());
        LocalTime time = entry.getValue().getTime();
        LocalDate dueDate = LocalDate.now().plusDays(term);
        boolean isExtended = entry.getValue().isExtendedTerm();
        boolean isApplyValid = service.apply_for_loan(term, amount);
        boolean isTimeValid = service.request_time_check(time, amount);

        if (isExtended) {
            term = service.extend_due_date(term);
            dueDate = dueDate.plusDays(term);
        }

        String decisionText = "Application for loan, sent by " + fullName.toUpperCase() +
                validationProcess(isApplyValid, isTimeValid);

        if (decisionText.contains("ACCEPTED")) {
            decisionText += "Requested amount is: " + amount + "\n"
                    + "Total cost of loan is: " + loanCost + "\n"
                    + "Requested due time is: " + time + "\n"
                    + "Loan repayment date is: " + dueDate + "\n";
        }
        return decisionText;
    }

    public String validationProcess(boolean isApplyValid, boolean isTimeValid) {

        String validation;
        if (isApplyValid) {
            if (!isTimeValid) {
                validation = " is REJECTED.\n"
                        + " Time of request for max amount is invalid. INFORMATION SENT TO CUSTOMER.";
                return validation;
            }
            validation = " is ACCEPTED.\n";
            return validation;
        }
        validation = " is REJECTED.\n"
                + "Requested values are not applicable.";
        return validation;
    }
}
