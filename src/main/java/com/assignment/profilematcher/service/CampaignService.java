package com.assignment.profilematcher.service;

import com.assignment.profilematcher.dto.ActiveCampaign;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CampaignService {

    private ActiveCampaign campaign;

    @PostConstruct
    void setupMockData() {
        // Mock campaigns
        campaign = new ActiveCampaign();
        campaign.setGame("mygame");
        campaign.setName("mycampaign");
        campaign.setPriority(10.5);
        campaign.setStartDate(LocalDateTime.parse("2022-01-25T00:00:00"));
        campaign.setEndDate(LocalDateTime.parse("2022-02-25T00:00:00"));
        campaign.setEnabled(true);
        campaign.setLastUpdated(LocalDateTime.parse("2021-07-13T11:46:58"));

        ActiveCampaign.Matchers matchers = getMatchers();

        campaign.setMatchers(matchers);
    }

    public ActiveCampaign getActiveCampaign() {
        // return mock campaign
        return campaign;
    }

    private ActiveCampaign.Matchers getMatchers() {
        ActiveCampaign.Matchers matchers = new ActiveCampaign.Matchers();
        ActiveCampaign.Range levelRange = new ActiveCampaign.Range();
        levelRange.setMin(1);
        levelRange.setMax(3);
        matchers.setLevel(levelRange);

        Map<String, List<String>> hasMap = new HashMap<>();
        hasMap.put("country", Arrays.asList("US", "RO", "CA"));
        hasMap.put("items", Collections.singletonList("item_1"));
        matchers.setHas(hasMap);

        Map<String, List<String>> doesNotHaveMap = new HashMap<>();
        doesNotHaveMap.put("items", Collections.singletonList("item_4"));
        matchers.setDoesNotHave(doesNotHaveMap);
        return matchers;
    }
}
