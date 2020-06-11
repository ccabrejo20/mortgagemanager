package com.example.mortgagemanager.resource.util;

public enum ErrorCode {

    NUMERIC_POSITIVE(501, "only numeric and positive values accepted"),
    DOWNPAYMENT_PURCHASE_PRCNTG(502, "downpayment cannot be higher than 10% of the property purchase price"),
    YEARS_OF_LOAN(503, "years of loan cannot be more than 30 years");

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
