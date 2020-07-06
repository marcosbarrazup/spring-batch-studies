package com.example.springbatch;


import com.example.springbatch.domain.Campaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService {


    @Autowired
    private CampaignRepository campaignRepository;

    public Campaign insert (Campaign campaign) {

        return campaignRepository.saveAndFlush(campaign);
    }

    public List<Campaign> findAll() {

        return campaignRepository.findAll();
    }

}
