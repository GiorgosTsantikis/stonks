package com.portfolio.tracker.stocktracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnnualStatementDTO {

    @JsonProperty("fiscal_year")
    private String fiscalYear;

    @JsonProperty("financials")
    private Financials financials;
}

class Financials {
    @JsonProperty("balance_sheet")
    private BalanceSheet balanceSheets;

    @JsonProperty("cash_flow_statement")
    private CashFlowStatement cashFlowStatement;

    @JsonProperty("income_statement")
    private IncomeStatement incomeStatement;

    @JsonProperty("comprehensive_income")
    private ComprehensiveIncome comprehensiveIncome;
}

class BalanceSheet {
    private Long fixedAssets;
    private Long otherCurrentAssets;
    private Long otherNonCurrentLiabilities;
    private Long otherCurrentLiabilities;
    private Long otherNonCurrentAssets;
    private Long currentAssets;
    private Long nonCurrentLiabilities;
    private Long cash;
    private Long equity;
    private Long currentLiabilities;
    private Long assets;
    private Long equityAttributableToParent;
    private Long accountsPayable;
    private Long wages;
    private Long longTermDebt;
    private Long equityAttributableToNoncontrollingInterest;
    private Long liabilities;
    private Long inventory;
    private Long liabilitiesAndEquity;


}
//END BALANCE SHEETS

class CashFlowStatement {
    private Long netCashFlowContinuing;
    private Long netCashFlowFromOperatingActivitiesContinuing;
    private Long netCashFlowFromInvestingActivitiesContinuing;
    private Long netCashFlowFromInvestingActivities;
    private Long netCashFlowFromFinancingActivities;
    private Long newCashFlowFromOperatiningActivities;
    private Long netCashFlow;
    private Long netCashFlowFromFinancingActivitiesContinuing;
}

//END CashFlowStatement

class IncomeStatement {
    private Long netIncomeLossAttributableToNonControllingInterest;
    private Long incomeTaxExpenseBenefitCurrent;
    private Long researchAndDevelopment;
    private Long grossProfit;
    private Long incomeTaxExpenseBenefitDeffered;
    private Double basicEarningsPerShare;
    private Long basicAverageShares;
    private Long participatingSecuritiesDistributesAndUndistributedEarningsLossBasic;
    private Long costsAndExpenses;
    private Long incomeLossFromContinuingOperationsBeforeTax;
    private Long netIncomeLossAttributableToParent;
    private Long operatingExpenses;
    private Long otherOperatingExpenses;
    private Long preferredStockDividendsAndOtherAdjustments;
    private Long operatingIncomeLoss;
    private Long incomeTaxExpenseBenefit;
    private Long revenues;
    private Long dilutedAverageShares;
    private Long costOfRevenue;
    private Long incomeLossFromContinuingOperationsAfterTax;
    private Long netIncomeLossAvailableToCommonStockholdersBasic;
    private Long benefitsCostsExpenses;
    private Long interestExpenseOperating;
    private Long netIncomeLoss;
    private Double dilutedEarningsPerShare;
    private Long nonoperatingIncomeLoss;
}
//END IncomeStatement

class ComprehensiveIncome {
    private Long otherComprehensiveIncomeLossAttributableToParent;
    private Long otherComprehensiveIncomeLoss;
    private Long comprehensiveIncomeLoss;
    private Long comprehensiveIncomeLossAttributableToParent;
    private Long comprehensiveIncomeLossAttributableToNoncontrollingInterest;
}

//END ComprehensiveIncome


