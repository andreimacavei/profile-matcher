package com.assignment.profilematcher.dao;

import com.assignment.profilematcher.model.PlayerProfile;
import org.springframework.data.repository.ListCrudRepository;

public interface PlayerProfileRepository extends ListCrudRepository<PlayerProfile, String> {
}
