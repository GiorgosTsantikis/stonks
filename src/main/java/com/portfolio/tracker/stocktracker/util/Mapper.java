package com.portfolio.tracker.stocktracker.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.tracker.stocktracker.dto.*;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    private static ObjectMapper mapper = new ObjectMapper();


    public static List<AnnualStatementDTO> MapFinancials(String json) throws JsonProcessingException {
        List<AnnualStatementDTO> list = new ArrayList<>();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode results = mapper.readTree(json);
        JsonNode array = results.get("results");
        for(JsonNode node : array) {
            JsonNode rootNode = node.get("financials");
            JsonNode cashFlowStatement = rootNode.get("cash_flow_statement");
            JsonNode comprehensiveIncome = rootNode.get("comprehensive_income");
            JsonNode incomeStatement = rootNode.get("income_statement");
            JsonNode balanceSheet = rootNode.get("balance_sheet");

            CashFlowStatement cashFlowStatementDTO = new CashFlowStatement();
            if (cashFlowStatement.get("net_cash_flow_from_investing_activities_continuing") != null) {
                cashFlowStatementDTO.setNetCashFlowFromInvestingActivitiesContinuing(
                        cashFlowStatement.get("net_cash_flow_from_investing_activities_continuing").get("value").asLong(0)
                );
            }

            if (cashFlowStatement.get("net_cash_flow_from_operating_activities_continuing") != null) {
                cashFlowStatementDTO.setNetCashFlowFromOperatingActivitiesContinuing(
                        cashFlowStatement.get("net_cash_flow_from_operating_activities_continuing").get("value").asLong(0)
                );
            }

            if (cashFlowStatement.get("net_cash_flow_from_operating_activities") != null) {
                cashFlowStatementDTO.setNewCashFlowFromOperatiningActivities(
                        cashFlowStatement.get("net_cash_flow_from_operating_activities").get("value").asLong(0)
                );
            }

            if (cashFlowStatement.get("net_cash_flow") != null) {
                cashFlowStatementDTO.setNetCashFlow(
                        cashFlowStatement.get("net_cash_flow").get("value").asLong(0)
                );
            }

            if (cashFlowStatement.get("net_cash_flow_from_financing_activities_continuing") != null) {
                cashFlowStatementDTO.setNetCashFlowFromFinancingActivitiesContinuing(
                        cashFlowStatement.get("net_cash_flow_from_financing_activities_continuing").get("value").asLong(0)
                );
            }

            if (cashFlowStatement.get("net_cash_flow_from_investing_activities") != null) {
                cashFlowStatementDTO.setNetCashFlowFromInvestingActivities(
                        cashFlowStatement.get("net_cash_flow_from_investing_activities").get("value").asLong(0)
                );
            }

            if (cashFlowStatement.get("net_cash_flow_from_financing_activities") != null) {
                cashFlowStatementDTO.setNetCashFlowFromFinancingActivities(
                        cashFlowStatement.get("net_cash_flow_from_financing_activities").get("value").asLong(0)
                );
            }

            if (cashFlowStatement.get("net_cash_flow_continuing") != null) {
                cashFlowStatementDTO.setNetCashFlowContinuing(
                        cashFlowStatement.get("net_cash_flow_continuing").get("value").asLong(0)
                );
            }

            ComprehensiveIncome comprehensiveIncomeDTO = new ComprehensiveIncome();
            if (comprehensiveIncome.get("other_comprehensive_income_loss") != null) {
                comprehensiveIncomeDTO.setOtherComprehensiveIncomeLoss(
                        comprehensiveIncome.get("other_comprehensive_income_loss").get("value").asLong(0)
                );
            }
            if (comprehensiveIncome.get("comprehensive_income_loss_attributable_to_noncontrolling_interest") != null) {
                comprehensiveIncomeDTO.setComprehensiveIncomeLossAttributableToNoncontrollingInterest(
                        comprehensiveIncome.get("comprehensive_income_loss_attributable_to_noncontrolling_interest").get("value").asLong(0)
                );
            }
            if (comprehensiveIncome.get("comprehensive_income_loss_attributable_to_parent") != null) {
                comprehensiveIncomeDTO.setComprehensiveIncomeLossAttributableToParent(
                        comprehensiveIncome.get("comprehensive_income_loss_attributable_to_parent").get("value").asLong(0)
                );
            }
            if(comprehensiveIncome.get("comprehensive_income_loss") != null){
                comprehensiveIncomeDTO.setComprehensiveIncomeLoss(
                        comprehensiveIncome.get("comprehensive_income_loss").get("value").asLong(0)
                );
            }
            if(comprehensiveIncome.get("other_comprehensive_income_loss_attributable_to_parent") != null){
                comprehensiveIncomeDTO.setOtherComprehensiveIncomeLossAttributableToParent(
                        comprehensiveIncome.get("other_comprehensive_income_loss_attributable_to_parent").get("value").asLong(0)
                );
            }

            IncomeStatement incomeStatementDTO = new IncomeStatement();
            if (incomeStatement.get("income_loss_from_continuing_operations_before_tax") != null) {
                incomeStatementDTO.setIncomeLossFromContinuingOperationsBeforeTax(
                        incomeStatement.get("income_loss_from_continuing_operations_before_tax").get("value").asLong(0)
                );
            }

            if (incomeStatement.get("participating_securities_distributed_and_undistributed_earnings_loss_basic") != null) {
                incomeStatementDTO.setParticipatingSecuritiesDistributesAndUndistributedEarningsLossBasic(
                        incomeStatement.get("participating_securities_distributed_and_undistributed_earnings_loss_basic").get("value").asLong(0)
                );
            }

            if (incomeStatement.get("benefits_costs_expenses") != null) {
                incomeStatementDTO.setBenefitsCostsExpenses(
                        incomeStatement.get("benefits_costs_expenses").get("value").asLong(0)
                );
            }

            if (incomeStatement.get("nonoperating_income_loss") != null) {
                incomeStatementDTO.setNonoperatingIncomeLoss(
                        incomeStatement.get("nonoperating_income_loss").get("value").asLong(0)
                );
            }

            if (incomeStatement.get("diluted_average_shares") != null) {
                incomeStatementDTO.setDilutedAverageShares(
                        incomeStatement.get("diluted_average_shares").get("value").asLong(0)
                );
            }

            if (incomeStatement.get("preferred_stock_dividends_other_adjustments") != null) {
                incomeStatementDTO.setPreferredStockDividendsAndOtherAdjustments(
                        incomeStatement.get("preferred_stock_dividends_other_adjustments").get("value").asLong(0)
                );
            }

            if (incomeStatement.get("income_tax_expense_benefit") != null) {
                incomeStatementDTO.setIncomeTaxExpenseBenefit(
                        incomeStatement.get("income_tax_expense_benefit").get("value").asLong(0)
                );
            }

            if (incomeStatement.get("research_and_development") != null) {
                incomeStatementDTO.setResearchAndDevelopment(
                        incomeStatement.get("research_and_development").get("value").asLong(0)
                );
            }

            if (incomeStatement.get("net_income_loss_attributable_to_parent") != null) {
                incomeStatementDTO.setNetIncomeLossAttributableToParent(
                        incomeStatement.get("net_income_loss_attributable_to_parent").get("value").asLong(0)
                );
            }

            if (incomeStatement.get("other_operating_expenses") != null) {
                incomeStatementDTO.setOtherOperatingExpenses(
                        incomeStatement.get("other_operating_expenses").get("value").asLong(0)
                );
            }

            if (incomeStatement.get("net_income_loss") != null) {
                incomeStatementDTO.setNetIncomeLoss(
                        incomeStatement.get("net_income_loss").get("value").asLong(0)
                );
            }

            if (incomeStatement.get("basic_earnings_per_share") != null) {
                incomeStatementDTO.setBasicEarningsPerShare(
                        incomeStatement.get("basic_earnings_per_share").get("value").asDouble(0.0)
                );
            }

            if (incomeStatement.get("income_tax_expense_benefit_deferred") != null) {
                incomeStatementDTO.setIncomeTaxExpenseBenefitDeffered(
                        incomeStatement.get("income_tax_expense_benefit_deferred").get("value").asLong(0)
                );
            }

            if (incomeStatement.get("basic_average_shares") != null) {
                incomeStatementDTO.setBasicAverageShares(
                        incomeStatement.get("basic_average_shares").get("value").asLong(0)
                );
            }

            if (incomeStatement.get("interest_expense_operating") != null) {
                incomeStatementDTO.setInterestExpenseOperating(
                        incomeStatement.get("interest_expense_operating").get("value").asLong(0)
                );
            }

            if (incomeStatement.get("net_income_loss_attributable_to_noncontrolling_interest") != null) {
                incomeStatementDTO.setNetIncomeLossAttributableToNonControllingInterest(
                        incomeStatement.get("net_income_loss_attributable_to_noncontrolling_interest").get("value").asLong(0)
                );
            }

            if (incomeStatement.get("costs_and_expenses") != null) {
                incomeStatementDTO.setCostsAndExpenses(
                        incomeStatement.get("costs_and_expenses").get("value").asLong(0)
                );
            }

            if (incomeStatement.get("operating_expenses") != null) {
                incomeStatementDTO.setOperatingExpenses(
                        incomeStatement.get("operating_expenses").get("value").asLong(0)
                );
            }

            if (incomeStatement.get("cost_of_revenue") != null) {
                incomeStatementDTO.setCostOfRevenue(
                        incomeStatement.get("cost_of_revenue").get("value").asLong(0)
                );
            }

            if (incomeStatement.get("income_loss_from_continuing_operations_after_tax") != null) {
                incomeStatementDTO.setIncomeLossFromContinuingOperationsAfterTax(
                        incomeStatement.get("income_loss_from_continuing_operations_after_tax").get("value").asLong(0)
                );
            }

            if (incomeStatement.get("diluted_earnings_per_share") != null) {
                incomeStatementDTO.setDilutedEarningsPerShare(
                        incomeStatement.get("diluted_earnings_per_share").get("value").asDouble(0.0)
                );
            }

            if (incomeStatement.get("net_income_loss_available_to_common_stockholders_basic") != null) {
                incomeStatementDTO.setNetIncomeLossAvailableToCommonStockholdersBasic(
                        incomeStatement.get("net_income_loss_available_to_common_stockholders_basic").get("value").asLong(0)
                );
            }

            if (incomeStatement.get("income_tax_expense_benefit_current") != null) {
                incomeStatementDTO.setIncomeTaxExpenseBenefitCurrent(
                        incomeStatement.get("income_tax_expense_benefit_current").get("value").asLong(0)
                );
            }

            if (incomeStatement.get("gross_profit") != null) {
                incomeStatementDTO.setGrossProfit(
                        incomeStatement.get("gross_profit").get("value").asLong(0)
                );
            }

            if (incomeStatement.get("revenues") != null) {
                incomeStatementDTO.setRevenues(
                        incomeStatement.get("revenues").get("value").asLong(0)
                );
            }

            if (incomeStatement.get("operating_income_loss") != null) {
                incomeStatementDTO.setOperatingIncomeLoss(
                        incomeStatement.get("operating_income_loss").get("value").asLong(0)
                );
            }

            BalanceSheet balanceSheetDTO = new BalanceSheet();
            if (balanceSheet.get("liabilities_and_equity") != null) {
                balanceSheetDTO.setLiabilitiesAndEquity(
                        balanceSheet.get("liabilities_and_equity").get("value").asLong(0)
                );
            }

            if (balanceSheet.get("fixed_assets") != null) {
                balanceSheetDTO.setFixedAssets(
                        balanceSheet.get("fixed_assets").get("value").asLong(0)
                );
            }

            if (balanceSheet.get("non_current_assets") != null) {
                balanceSheetDTO.setNonCurrentAssets(
                        balanceSheet.get("non_current_assets").get("value").asLong(0)
                );
            }

            if (balanceSheet.get("wages") != null) {
                balanceSheetDTO.setWages(
                        balanceSheet.get("wages").get("value").asLong(0)
                );
            }

            if (balanceSheet.get("non_current_liabilities") != null) {
                balanceSheetDTO.setNonCurrentLiabilities(
                        balanceSheet.get("non_current_liabilities").get("value").asLong(0)
                );
            }

            if (balanceSheet.get("assets") != null) {
                balanceSheetDTO.setAssets(
                        balanceSheet.get("assets").get("value").asLong(0)
                );
            }

            if (balanceSheet.get("inventory") != null) {
                balanceSheetDTO.setInventory(
                        balanceSheet.get("inventory").get("value").asLong(0)
                );
            }

            if (balanceSheet.get("other_non_current_assets") != null) {
                balanceSheetDTO.setOtherNonCurrentAssets(
                        balanceSheet.get("other_non_current_assets").get("value").asLong(0)
                );
            }

            if (balanceSheet.get("other_current_assets") != null) {
                balanceSheetDTO.setOtherCurrentAssets(
                        balanceSheet.get("other_current_assets").get("value").asLong(0)
                );
            }

            if (balanceSheet.get("long_term_debt") != null) {
                balanceSheetDTO.setLongTermDebt(
                        balanceSheet.get("long_term_debt").get("value").asLong(0)
                );
            }

            if (balanceSheet.get("equity_attributable_to_parent") != null) {
                balanceSheetDTO.setEquityAttributableToParent(
                        balanceSheet.get("equity_attributable_to_parent").get("value").asLong(0)
                );
            }

            if (balanceSheet.get("equity") != null) {
                balanceSheetDTO.setEquity(
                        balanceSheet.get("equity").get("value").asLong(0)
                );
            }

            if (balanceSheet.get("other_non_current_liabilities") != null) {
                balanceSheetDTO.setOtherNonCurrentLiabilities(
                        balanceSheet.get("other_non_current_liabilities").get("value").asLong(0)
                );
            }

            if (balanceSheet.get("equity_attributable_to_noncontrolling_interest") != null) {
                balanceSheetDTO.setEquityAttributableToNoncontrollingInterest(
                        balanceSheet.get("equity_attributable_to_noncontrolling_interest").get("value").asLong(0)
                );
            }

            if (balanceSheet.get("current_assets") != null) {
                balanceSheetDTO.setCurrentAssets(
                        balanceSheet.get("current_assets").get("value").asLong(0)
                );
            }

            if (balanceSheet.get("cash") != null) {
                balanceSheetDTO.setCash(
                        balanceSheet.get("cash").get("value").asLong(0)
                );
            }

            if (balanceSheet.get("current_liabilities") != null) {
                balanceSheetDTO.setCurrentLiabilities(
                        balanceSheet.get("current_liabilities").get("value").asLong(0)
                );
            }

            if (balanceSheet.get("other_current_liabilities") != null) {
                balanceSheetDTO.setOtherCurrentLiabilities(
                        balanceSheet.get("other_current_liabilities").get("value").asLong(0)
                );
            }

            if (balanceSheet.get("accounts_payable") != null) {
                balanceSheetDTO.setAccountsPayable(
                        balanceSheet.get("accounts_payable").get("value").asLong(0)
                );
            }

            if (balanceSheet.get("liabilities") != null) {
                balanceSheetDTO.setLiabilities(
                        balanceSheet.get("liabilities").get("value").asLong(0)
                );
            }


            AnnualStatementDTO annualStatementDTO = new AnnualStatementDTO();
            Financials financialsDTO = new Financials();
            financialsDTO.setIncomeStatement(incomeStatementDTO);
            financialsDTO.setBalanceSheets(balanceSheetDTO);
            financialsDTO.setCashFlowStatement(cashFlowStatementDTO);
            financialsDTO.setComprehensiveIncome(comprehensiveIncomeDTO);

            annualStatementDTO.setFinancials(financialsDTO);
            annualStatementDTO.setFiscalYear(node.get("fiscal_year").asText());
            list.add(annualStatementDTO);
        }

        return list;
    }
}
