package com.example.mortgagemanager.validator;

import com.example.mortgagemanager.resource.util.ErrorCode;
import com.example.mortgagemanager.resource.exception.ErrorResource;
import com.example.mortgagemanager.resource.MonthlyMortgageRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Validator {
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    public void validate(MonthlyMortgageRequest monthlyMortgageRequest) {
        if (monthlyMortgageRequest.getDownPaymentLoan().intValue() < 0 ||
                monthlyMortgageRequest.getPropertyPurchasePrice().intValue() < 0 ||
                monthlyMortgageRequest.getAnnualInterestRate().intValue() < 0 ||
                monthlyMortgageRequest.getLengthOfLoan() < 0) {
            generateException(ErrorCode.NUMERIC_POSITIVE);
        }

        BigDecimal percentagePurchasePrice = calculatePercentage(monthlyMortgageRequest.getPropertyPurchasePrice(), new BigDecimal(10));

        if (monthlyMortgageRequest.getDownPaymentLoan().compareTo(percentagePurchasePrice) == 1) {
            generateException(ErrorCode.DOWNPAYMENT_PURCHASE_PRCNTG);
        }

        if (monthlyMortgageRequest.getLengthOfLoan() > 30) {
            generateException(ErrorCode.YEARS_OF_LOAN);
        }

    }

    private void generateException(ErrorCode errorCode) {
        throw new ErrorResource(errorCode.getCode(), errorCode.getMessage());
    }

    private BigDecimal calculatePercentage(BigDecimal base, BigDecimal pct) {
        return base.multiply(pct).divide(ONE_HUNDRED);
    }
}
