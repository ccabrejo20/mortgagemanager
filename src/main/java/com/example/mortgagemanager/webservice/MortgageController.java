package com.example.mortgagemanager.webservice;

import com.example.mortgagemanager.resource.exception.ErrorResource;
import com.example.mortgagemanager.resource.MonthlyMortgageRequest;
import com.example.mortgagemanager.resource.MonthlyMortgageResponse;
import com.example.mortgagemanager.validator.Validator;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class MortgageController {

    @Autowired
    private Validator validator;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful response", response = MonthlyMortgageResponse.class),
            @ApiResponse(code = 501, message = "only numeric and positive values accepted", response = ErrorResource.class),
            @ApiResponse(code = 502, message = "downpayment cannot be higher than 10% of the property purchase price", response = ErrorResource.class),
            @ApiResponse(code = 503, message = "years of loan cannot be more than 30 years", response = ErrorResource.class)
    })
    @PostMapping(value = "/calculateMonthlyMortgage", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public MonthlyMortgageResponse calculateMonthlyMortgage(@RequestBody MonthlyMortgageRequest monthlyMortgageRequest) {
        validator.validate(monthlyMortgageRequest);
        return new MonthlyMortgageResponse(calculateMortgage(monthlyMortgageRequest));
    }

    private BigDecimal calculateMortgage(MonthlyMortgageRequest monthlyMortgageRequest) {

        BigDecimal downPaymentLoan = monthlyMortgageRequest.getDownPaymentLoan();
        BigDecimal interestRate = monthlyMortgageRequest.getAnnualInterestRate();
        Integer lengthOfLoan = monthlyMortgageRequest.getLengthOfLoan();

        double parcial = Math.pow((1 + interestRate.doubleValue()), lengthOfLoan);
        return BigDecimal.valueOf(downPaymentLoan.doubleValue() * (interestRate.doubleValue() * parcial / (parcial - 1)));
    }
}
