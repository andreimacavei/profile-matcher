package com.assignment.profilematcher.service;

import com.assignment.profilematcher.dao.CampaignRepository;
import com.assignment.profilematcher.dao.PlayerProfileRepository;
import com.assignment.profilematcher.model.Campaign;
import com.assignment.profilematcher.model.PlayerProfile;
import com.assignment.profilematcher.dto.ActiveCampaign;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;


@Service
public class ProfileMatcherServiceImpl implements ProfileMatcherService {

    PlayerProfileRepository playerProfileRepository;

    CampaignRepository campaignRepository;

    CampaignService campaignService;

    public ProfileMatcherServiceImpl(PlayerProfileRepository playerProfileRepository, CampaignService campaignService, CampaignRepository campaignRepository) {
        this.playerProfileRepository = playerProfileRepository;
        this.campaignService = campaignService;
        this.campaignRepository = campaignRepository;
    }

    @Override
    public PlayerProfile matchProfile(String playerId) {
        // Logic to get and return the player profile
        PlayerProfile playerProfile = playerProfileRepository.findById(playerId).orElse(null);
        if (playerProfile != null) {
            ActiveCampaign activeCampaign = campaignService.getActiveCampaign();

            if (isProfileEligibleForCampaign(playerProfile, activeCampaign)) {
                updateProfileWithCampaign(playerProfile, activeCampaign);
            }
        }


        return playerProfile;
    }

    private boolean isProfileEligibleForCampaign(PlayerProfile profile, ActiveCampaign activeCampaign) {
        ActiveCampaign.Matchers matchers = activeCampaign.getMatchers();

        // Check level range
        if (profile.getLevel() < matchers.getLevel().getMin() || profile.getLevel() > matchers.getLevel().getMax()) {
            return false;
        }

        // Check has: country, items
        Map<String, List<String>> hasMap =  matchers.getHas();

        if (!hasMap.get("country").contains(profile.getCountry())) {
            return false;
        }

        AtomicBoolean match = new AtomicBoolean(true);
        hasMap.get("items").forEach(item -> {
            if (!profile.getInventory().containsKey(item) || profile.getInventory().get(item).getQuantity() == 0) {
                match.set(false);
            }
        });
        if (!match.get()) {
            return false;
        }

        // Check does not have items
        Map<String, List<String>> doesNotHaveMap =  matchers.getDoesNotHave();
        match.set(true);
        doesNotHaveMap.get("items").forEach(item -> {
            if (profile.getInventory().containsKey(item) && profile.getInventory().get(item).getQuantity() > 0) {
                match.set(false);
            }
        });
        return match.get();// all conditions match
    }


    @Transactional
    protected PlayerProfile updateProfileWithCampaign(PlayerProfile playerProfile, ActiveCampaign activeCampaign) {
        // Update profile with activeCampaign name if not already present
        Set<Campaign> playerCampaigns = playerProfile.getActiveCampaigns();
        if (playerCampaigns.stream().noneMatch(c -> c.getName().equals(activeCampaign.getName()))) {
            Campaign newCampaign = new Campaign();
            newCampaign.setName(activeCampaign.getName());
            playerProfile.getActiveCampaigns().add(newCampaign);
        }

        return playerProfileRepository.save(playerProfile);
    }


}
