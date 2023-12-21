package com.assignment.profilematcher.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActiveCampaign {

    private String game;
    private String name;
    private double priority;
    private Matchers matchers;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean enabled;
    private LocalDateTime lastUpdated;

    @Getter @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Matchers {
        private Range level;
        private Map<String, List<String>> has;
        private Map<String, List<String>> doesNotHave;
    }

    @Getter @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Range {
        private int min;
        private int max;
    }
}
