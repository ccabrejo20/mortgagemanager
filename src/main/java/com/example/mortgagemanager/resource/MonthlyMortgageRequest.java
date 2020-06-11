package com.example.mortgagemanager.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MonthlyMortgageRequest {

    @NotNull
    private BigDecimal downPaymentLoan;

    @NotNull
    private BigDecimal propertyPurchasePrice;

    @NotNull
    private BigDecimal annualInterestRate;

    @NotNull
    private Integer lengthOfLoan;
}
