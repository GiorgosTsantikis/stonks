package com.portfolio.tracker.stocktracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnnualStatementDTO {

    @JsonProperty("fiscal_year")
    private String fiscalYear;

    @JsonProperty("financials")
    private Financials financials;

    public String getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public Financials getFinancials() {
        return financials;
    }

    public void setFinancials(Financials financials) {
        this.financials = financials;
    }
}

//END BALANCE SHEETS

//END CashFlowStatement

//END IncomeStatement

//END ComprehensiveIncome


