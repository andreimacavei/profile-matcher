package com.assignment.profilematcher.dao;

import com.assignment.profilematcher.model.Campaign;
import org.springframework.data.repository.ListCrudRepository;

public interface CampaignRepository extends ListCrudRepository<Campaign, String> {
}
