package org.bank.loans;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationProcessorTest {

    @Nested
    @DisplayName("Testing validationProcess method")
    class validationProcessTests {

        @Test
        void validationProcess_withInvalidApply_shouldReturnRejectedBecauseValues() {

            //given
            ApplicationProcessor toTest = new ApplicationProcessor();

            //when
            boolean invalidApply = false;
            boolean validTime = true;
            String expected = " is REJECTED.\n"
                    + "Requested values are not applicable.\n";
            String result = toTest.validationProcess(invalidApply, validTime);

            //then
            assertEquals(expected, result);
        }

        @Test
        void validationProcess_withInvalidTime_shouldReturnRejectedBecauseTime() {

            //given
            ApplicationProcessor toTest = new ApplicationProcessor();

            //when
            boolean validApply = true;
            boolean invalidTime = false;
            String expected = " is REJECTED.\n"
                    + "Time of request for max amount is invalid. INFORMATION SENT TO CUSTOMER.\n";
            String result = toTest.validationProcess(validApply, invalidTime);

            //then
            assertEquals(expected, result);
        }

        @Test
        void validationProcess_withAllValid_shouldReturnAccepted() {

            //given
            ApplicationProcessor toTest = new ApplicationProcessor();

            //when
            boolean validApply = true;
            boolean validTime = true;
            String expected = " is ACCEPTED.\n";
            String result = toTest.validationProcess(validApply, validTime);

            //then
            assertEquals(expected, result);
        }
    }

    @Nested
    @DisplayName("Testing makeDecision method")
    class makeDecisionTests {

        String finalAcceptationTextMaker(String uppercaseName, double amount, double loanCost,
                                         int dueTime, LocalDate dueDate) {

            String result = "Application for loan, sent by " + uppercaseName + " is ACCEPTED.\n" +
                    "Requested amount is: " + amount + "\n" +
                    "Total cost of loan is: " + loanCost + "\n" +
                    "Requested due time is: " + dueTime + " days\n" +
                    "Loan repayment date is: " + dueDate + "\n";
            return result;
        }

//        private Map<Customer, ApplicationData> mapToTestWith = new HashMap<>() {{
//            put(new Customer("Invalid Apply", "0000"),
//                    new ApplicationData(21, 900.0, LocalTime.of(14, 0), false));
//            put(new Customer("Invalid Time", "1111"),
//                    new ApplicationData(21, 80000, LocalTime.of(0, 1), false));
//            put(new Customer("Valid Application", "2222"),
//                    new ApplicationData(21, 1000, LocalTime.of(14, 1), false));
//            put(new Customer("Extended Application", "3333"),
//                    new ApplicationData(21, 1000, LocalTime.of(14, 1), false));
//        }};

        @Test
        void makeDecision_withInvalidApply_shouldReturnProperRejectionText() {

            //given
            ApplicationProcessor toTest = new ApplicationProcessor();
            Map.Entry<Customer, ApplicationData> toTestWith = new Map.Entry<Customer, ApplicationData>() {
                @Override
                public Customer getKey() {
                    return new Customer("Invalid Apply", "0000");
                }
                @Override
                public ApplicationData getValue() {
                    return new ApplicationData(21, 900.0, LocalTime.of(14, 0), false);
                }
                @Override
                public ApplicationData setValue(ApplicationData value) {
                    return null;
                }
            };

            //when
            String expected = "Application for loan, sent by INVALID APPLY is REJECTED.\n"
                    + "Requested values are not applicable.\n";
            String result = toTest.makeDecision(toTestWith);

            //then
            assertEquals(expected, result);
        }

        @Test
        void makeDecision_withInvalidTime_shouldReturnProperRejectionText() {

            //given
            ApplicationProcessor toTest = new ApplicationProcessor();
            Map.Entry<Customer, ApplicationData> toTestWith = new Map.Entry<Customer, ApplicationData>() {
                @Override
                public Customer getKey() {
                    return new Customer("Invalid Time", "0000");
                }
                @Override
                public ApplicationData getValue() {
                    return new ApplicationData(21, 80000, LocalTime.of(0, 1), false);
                }
                @Override
                public ApplicationData setValue(ApplicationData value) {
                    return null;
                }
            };

            //when
            String expected = "Application for loan, sent by INVALID TIME is REJECTED.\n"
                    + "Time of request for max amount is invalid. INFORMATION SENT TO CUSTOMER.\n";
            String result = toTest.makeDecision(toTestWith);

            //then
            assertEquals(expected, result);

        }

        @Test
        void makeDecision_withValidApply_shouldReturnProperAcceptationText() {

            //given
            ApplicationProcessor toTest = new ApplicationProcessor();
            Map.Entry<Customer, ApplicationData> toTestWith = new Map.Entry<Customer, ApplicationData>() {
                @Override
                public Customer getKey() {
                    return new Customer("Valid Application", "0000");
                }
                @Override
                public ApplicationData getValue() {
                    return new ApplicationData(21, 10000, LocalTime.of(14, 1), false);
                }
                @Override
                public ApplicationData setValue(ApplicationData value) {
                    return null;
                }
            };
            //when
            String expected = finalAcceptationTextMaker("VALID APPLICATION", 10000,
                    1000, 21, LocalDate.now().plusDays(21));
            String result = toTest.makeDecision(toTestWith);

            //then
            assertEquals(expected, result);
        }

        @Test
        void makeDecision_withValidApplyAndExtension_shouldReturnProperAcceptationTextAndDueDate() {

            //given
            ApplicationProcessor toTest = new ApplicationProcessor();
            Map.Entry<Customer, ApplicationData> toTestWith = new Map.Entry<Customer, ApplicationData>() {
                @Override
                public Customer getKey() {
                    return new Customer("Extended Application", "0000");
                }
                @Override
                public ApplicationData getValue() {
                    return new ApplicationData(21, 10000, LocalTime.of(14, 1), true);
                }
                @Override
                public ApplicationData setValue(ApplicationData value) {
                    return null;
                }
            };
            //when
            String expected = finalAcceptationTextMaker("EXTENDED APPLICATION", 10000,
                    1000, 28, LocalDate.now().plusDays(28));
            String result = toTest.makeDecision(toTestWith);

            //then
            assertEquals(expected, result);
        }
    }


}