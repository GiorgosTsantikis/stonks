package com.portfolio.tracker.stocktracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Financials {
    @JsonProperty("balance_sheet")
    private BalanceSheet balanceSheets;

    @JsonProperty("cash_flow_statement")
    private CashFlowStatement cashFlowStatement;

    @JsonProperty("income_statement")
    private IncomeStatement incomeStatement;

    @JsonProperty("comprehensive_income")
    private ComprehensiveIncome comprehensiveIncome;

    public BalanceSheet getBalanceSheets() {
        return balanceSheets;
    }

    public void setBalanceSheets(BalanceSheet balanceSheets) {
        this.balanceSheets = balanceSheets;
    }

    public CashFlowStatement getCashFlowStatement() {
        return cashFlowStatement;
    }

    public void setCashFlowStatement(CashFlowStatement cashFlowStatement) {
        this.cashFlowStatement = cashFlowStatement;
    }

    public IncomeStatement getIncomeStatement() {
        return incomeStatement;
    }

    public void setIncomeStatement(IncomeStatement incomeStatement) {
        this.incomeStatement = incomeStatement;
    }

    public ComprehensiveIncome getComprehensiveIncome() {
        return comprehensiveIncome;
    }

    public void setComprehensiveIncome(ComprehensiveIncome comprehensiveIncome) {
        this.comprehensiveIncome = comprehensiveIncome;
    }
}
