package com.assignment.profilematcher.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
class Clan {
    private String id;
    private String name;
}
