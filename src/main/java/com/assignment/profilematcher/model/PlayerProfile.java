package com.assignment.profilematcher.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity(name = "player_profiles")
@Getter
@Setter
public class PlayerProfile {
    @Id
    @Column(name = "player_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String credential;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime last_session;

    private double totalSpent;
    private double totalRefund;
    private int totalTransactions;
    private LocalDateTime lastPurchase;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "player_campaigns",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "campaign_id")
    )
    @JsonManagedReference
    private Set<Campaign> activeCampaigns;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "playerProfile")
    @JsonManagedReference
    private List<Device> devices;

    private int level;
    private int xp;
    private int totalPlaytime;

    private String country;
    private String language;
    private LocalDateTime birthdate;
    private String gender;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "playerProfile")
    @MapKey(name = "item_name")
    @JsonManagedReference
    private Map<String, InventoryItem> inventory;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @jakarta.persistence.Column(name = "clan_id")),
            @AttributeOverride(name = "name", column = @jakarta.persistence.Column(name = "clan_name"))
    })
    private Clan clan;

    @Column(name = "_customfield")
    private String customField;
}
