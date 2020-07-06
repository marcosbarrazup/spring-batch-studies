package com.example.springbatch;

import com.example.springbatch.domain.Campaign;
import com.example.springbatch.domain.CampaignType;
import com.example.springbatch.domain.Cashback;
import com.example.springbatch.domain.Customer;
import com.example.springbatch.exceptions.CustomerNotFoundException;
import com.example.springbatch.exceptions.ThereIsNoEligibleCampaignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CashbackService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CashbackRepository cashbackRepository;
    @Autowired
    private CampaignRepository campaignRepository;
    private static final CurrencyUnit brlCurrency = Monetary.getCurrency("BRL");


    public Cashback doCashback(Cashback cashback) {

        List<Campaign> campaigns = getCampaignsForThisDate();
        Customer customer = getCustomerByCashback(cashback);
        Campaign campaign = getEligibleCampaign(cashback, campaigns, customer);

        Double cashbackValue = BigDecimal.valueOf(cashback.getTransactionValue() * (campaign.getCashbackPercentage())).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
        cashback.setValue(cashbackValue);
        cashback.setCustomer(customer);
        cashback.setCampaign(campaign);
        cashback.setCreatedAt(LocalDateTime.now());
        customer.deposit(cashbackValue);
        customer.getCashbacks().add(cashback);

        return cashback;
    }

    private Campaign getEligibleCampaign(Cashback cashback, List<Campaign> campaigns, Customer customer) {
        List<Campaign> eligibleCampaigns = campaigns.stream()
                .filter(campaign -> this.isCashbackEligibleForCampaign(cashback, campaign, customer))
                .collect(Collectors.toList());

        if (eligibleCampaigns.isEmpty())
            throw new ThereIsNoEligibleCampaignException("There is no one eligible campaign for this customer!");
        return eligibleCampaigns.stream().findFirst().get();
    }

    private Customer getCustomerByCashback(Cashback cashback) {
        Optional<Customer> customer = customerRepository.findByCpf(cashback.getCustomer().getCpf());
        if (customer.isEmpty()) throw new CustomerNotFoundException("Customer not found!");
        return customer.get();
    }

    private List<Campaign> getCampaignsForThisDate() {
        List<Campaign> campaigns = campaignRepository.findAllByStartDateBeforeAndEndDateAfter(LocalDate.now(), LocalDate.now());

        if (campaigns.isEmpty())
            throw new ThereIsNoEligibleCampaignException("There is no one campaign for this date!");
        return campaigns;
    }


    private Boolean isCashbackEligibleForCampaign(Cashback cashback, Campaign campaign, Customer customer) {


        Double cashbackValue = cashback.getTransactionValue() * campaign.getCashbackPercentage();

        List<Cashback> customerCashbacksForThisCampaign =
                customer.getCashbacks().stream()
                        .filter(customerCashback -> customerCashback.getCampaign().getId().equals(campaign.getId()))
                        .collect(Collectors.toList());

        if (campaign.getHasValueLimit() && campaign.getBalance() < cashbackValue) return false;

        if (campaign.getType().equals(CampaignType.AMOUNT_OF_CASHBACKS)) {
            return !(campaign.getMinimumTransactionValue() > cashback.getTransactionValue())
                    && !(campaign.getMaximumTransactionValue() < cashback.getTransactionValue())
                    && !(campaign.getCashbacksByCustomer() < customerCashbacksForThisCampaign.size());
        } else {
            Double customerTotalValueForThisCampaign = 0.0;
            for (Cashback customerCashback : customerCashbacksForThisCampaign) {
                customerTotalValueForThisCampaign += customerCashback.getValue();
            }
            return !((customerTotalValueForThisCampaign + cashbackValue) > campaign.getMaximumValueAllowedByCustomer());
        }

    }
}
