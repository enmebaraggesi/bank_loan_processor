package org.bank.loans;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class LoanApplicationServiceTest {

    @Nested
    @DisplayName("Testing applyForLoan method")
    class applyForLoanTests {

        @Test
        void applyForLoan_withInvalidTerm_shouldReturnFalse() {

            //given
            LoanApplicationService toTest = new LoanApplicationService();

            //when
            long invalidTerm = 121;
            double validAmount = 1000.0;
            boolean result = toTest.applyForLoan(invalidTerm, validAmount);

            //then
            assertFalse(result);
        }

        @Test
        void applyForLoan_withInvalidAmount_shouldReturnFalse() {

            //given
            LoanApplicationService toTest = new LoanApplicationService();

            //when
            long invalidTerm = 120;
            double validAmount = 81000.0;
            boolean result = toTest.applyForLoan(invalidTerm, validAmount);

            //then
            assertFalse(result);
        }

        @Test
        void applyForLoan_withValidArguments_shouldReturnTrue() {

            //given
            LoanApplicationService toTest = new LoanApplicationService();

            //when
            long invalidTerm = 120;
            double validAmount = 80000.0;
            boolean result = toTest.applyForLoan(invalidTerm, validAmount);

            //then
            assertTrue(result);
        }
    }

    @Nested
    @DisplayName("Testing requestTimeCheck method")
    class requestTimeCheckTests {

        @Test
        void requestTimeCheck_withValidTimeAndMaxAmount_shouldReturnTrue() {

            //given
            LoanApplicationService toTest = new LoanApplicationService();

            //when
            LocalTime validTime = LocalTime.of(6,1);
            double maxAmount = 80000.0;
            boolean result = toTest.requestTimeCheck(validTime, maxAmount);

            //then
            assertTrue(result);
        }

        @Test
        void requestTimeCheck_withInvalidTimeAndValidAmount_shouldReturnTrue() {

            //given
            LoanApplicationService toTest = new LoanApplicationService();

            //when
            LocalTime validTime = LocalTime.of(0,1);
            double maxAmount = 1000.0;
            boolean result = toTest.requestTimeCheck(validTime, maxAmount);

            //then
            assertTrue(result);
        }

        @Test
        void requestTimeCheck_withInvalidTimeAndMaxAmount_shouldReturnFalse() {

            //given
            LoanApplicationService toTest = new LoanApplicationService();

            //when
            LocalTime validTime = LocalTime.of(0,1);
            double maxAmount = 80000.0;
            boolean result = toTest.requestTimeCheck(validTime, maxAmount);

            //then
            assertFalse(result);
        }
    }

    @Nested
    @DisplayName("Testing costOfLoan method")
    class costOfLoanTests {

        @Test
        void costOfLoan_with1000_shouldReturn100() {

            //given
            LoanApplicationService toTest = new LoanApplicationService();

            //when
            double amount = 1000.0;
            double result = toTest.costOfLoan(amount);

            //then
            assertEquals(100, result);
        }

        @Test
        void costOfLoan_with80000_shouldReturn8000() {

            //given
            LoanApplicationService toTest = new LoanApplicationService();

            //when
            double amount = 80000.0;
            double result = toTest.costOfLoan(amount);

            //then
            assertEquals(8000, result);
        }
    }

    @Nested
    @DisplayName("Testing extendDueDate method")
    class extendDueDateTests {

        @Test
        void extendDueDate_with100Days_shouldReturn107() {

            //given
            LoanApplicationService toTest = new LoanApplicationService();

            //when
            long term100 = 100;
            long result = toTest.extendDueDate(term100);

            //then
            assertEquals(107, result);
        }

        @Test
        void extendDueDate_withMaxDays_shouldReturnMaxDays() {

            //given
            LoanApplicationService toTest = new LoanApplicationService();

            //when
            long term100 = 120;
            long result = toTest.extendDueDate(term100);

            //then
            assertEquals(120, result);
        }
    }
}