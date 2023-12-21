CREATE
DATABASE  IF NOT EXISTS `player_profiles_directory` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE
`player_profiles_directory`;

DROP TABLE IF EXISTS player_profiles;

CREATE TABLE player_profiles
(
    player_id          VARCHAR(255) PRIMARY KEY,
    credential         VARCHAR(255),
    created            TIMESTAMP,
    modified           TIMESTAMP,
    last_session       TIMESTAMP,
    total_spent        DECIMAL,
    total_refund       DECIMAL,
    total_transactions INT,
    last_purchase      TIMESTAMP,
    level              INT,
    xp                 INT,
    total_playtime     INT,
    country            VARCHAR(255),
    language           VARCHAR(255),
    birthdate          TIMESTAMP,
    gender             VARCHAR(50),
    clan_id            VARCHAR(255),
    clan_name          VARCHAR(255),
    _customfield       VARCHAR(255)
);

INSERT INTO player_profiles (player_id,
                             credential,
                             created,
                             modified,
                             last_session,
                             total_spent,
                             total_refund,
                             total_transactions,
                             last_purchase,
                             level,
                             xp,
                             total_playtime,
                             country,
                             language,
                             birthdate,
                             gender,
                             clan_id,
                             clan_name,
                             _customfield)
VALUES ('97983be2-98b7-11e7-90cf-082e5f28d836',
        'apple_credential',
        '2021-01-10 13:37:17',
        '2021-01-23 13:37:17',
        '2021-01-23 13:37:17',
        400,
        0,
        5,
        '2021-01-22 13:37:17',
        3,
        1000,
        144,
        'CA',
        'fr',
        '2000-01-10 13:37:17',
        'male',
        '123456',
        'Hello world clan',
        'mycustom');



DROP TABLE IF EXISTS devices;

CREATE TABLE devices
(
    id        INT PRIMARY KEY,
    player_id VARCHAR(255),
    model     VARCHAR(255),
    carrier   VARCHAR(255),
    firmware  VARCHAR(255),
    FOREIGN KEY (player_id) REFERENCES player_profiles (player_id)
);

INSERT INTO devices (id, player_id, model, carrier, firmware)
VALUES (1, '97983be2-98b7-11e7-90cf-082e5f28d836', 'apple iphone 11', 'vodafone', '123');

DROP TABLE IF EXISTS inventory_items;
CREATE TABLE inventory_items
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    player_id VARCHAR(255),
    item_name VARCHAR(255),
    quantity  INT,
    FOREIGN KEY (player_id) REFERENCES player_profiles (player_id)
);

INSERT INTO inventory_items (player_id, item_name, quantity)
VALUES ('97983be2-98b7-11e7-90cf-082e5f28d836', 'item_1', 1),
       ('97983be2-98b7-11e7-90cf-082e5f28d836', 'item_34', 3),
       ('97983be2-98b7-11e7-90cf-082e5f28d836', 'item_55', 2);

DROP TABLE IF EXISTS active_campaigns;
CREATE TABLE active_campaigns
(
    campaign_id INT AUTO_INCREMENT PRIMARY KEY,
    game        VARCHAR(255),
    name        VARCHAR(255)
);

INSERT INTO active_campaigns (campaign_id, game, name)
       VALUES (1, 'mygame', 'mycampaign'),
              (2, 'game_1', 'campaign_1');

DROP TABLE IF EXISTS player_campaigns;
CREATE TABLE player_campaigns
(
    player_id   VARCHAR(255),
    campaign_id INT,
    FOREIGN KEY (player_id) REFERENCES player_profiles (player_id),
    FOREIGN KEY (campaign_id) REFERENCES active_campaigns (campaign_id),
    PRIMARY KEY (player_id, campaign_id)
);

INSERT INTO player_campaigns (player_id, campaign_id)
VALUES ('97983be2-98b7-11e7-90cf-082e5f28d836', 1),
       ('97983be2-98b7-11e7-90cf-082e5f28d836', 2);
