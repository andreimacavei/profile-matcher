package com.assignment.profilematcher.controller;

import com.assignment.profilematcher.model.PlayerProfile;
import com.assignment.profilematcher.service.ProfileMatcherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProfileMatcherController {


    private ProfileMatcherService profileMatcherService;

    public ProfileMatcherController(ProfileMatcherService profileMatcherService) {
        this.profileMatcherService = profileMatcherService;
    }

    @GetMapping("/get_client_config/{player_id}")
    public ResponseEntity<Object> getClientConfig(@PathVariable String player_id) {
        // Logic to get and return the player profile
        PlayerProfile playerProfile = profileMatcherService.matchProfile(player_id);

        if (playerProfile == null) {
            String errorMessage = String.format("Player with id %s not found", player_id);
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(playerProfile);
    }
}
