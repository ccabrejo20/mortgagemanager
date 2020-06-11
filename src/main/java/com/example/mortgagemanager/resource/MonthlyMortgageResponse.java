package com.example.mortgagemanager.resource;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class MonthlyMortgageResponse {
    private BigDecimal result;
}
