package com.example.springbatch.controllers;


import com.example.springbatch.request.CampaignRequest;
import com.example.springbatch.CampaignService;
import com.example.springbatch.domain.Campaign;
import com.example.springbatch.dto.CampaignDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/campaigns")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CampaignDTO insert(@Validated @RequestBody CampaignRequest campaignRequest) {

        Campaign campaign = new Campaign(
                campaignRequest.getMinimumTransactionValue(),
                campaignRequest.getMaximumTransactionValue(),
                campaignRequest.getCashbackPercentage(),
                campaignRequest.getStartDate(),
                campaignRequest.getEndDate(),
                campaignRequest.getType(),
                campaignRequest.getMaximumValueAllowedByCustomer(),
                campaignRequest.getCashbacksByCustomer(),
                campaignRequest.getHasValueLimit(),
                campaignRequest.getMaximumValueAllowedByCampaign()
        );

        return new CampaignDTO(campaignService.insert(campaign));
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<CampaignDTO> findAll() {

        List<Campaign> campaigns = campaignService.findAll();
        return campaigns.stream().map(CampaignDTO::new).collect(Collectors.toList());
    }
}
