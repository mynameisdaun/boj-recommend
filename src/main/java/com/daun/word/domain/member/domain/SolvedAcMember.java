package com.daun.word.domain.member.domain;

import com.daun.word.global.infra.solvedac.dto.UserSearchResponse;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;

@Getter
@ToString
public class SolvedAcMember {
    private String handle;
    private String bio;
    private Organization[] organizations;
    private Badge badge;
    private Background background;
    private String profileImageUrl;
    private int solvedCount;
    private int voteCount;
    private int exp;
    private int tier;
    private int rating;
    private int ratingByProblemsSum;
    private int ratingByClass;
    private int ratingBySolvedCount;
    private int ratingByVoteCount;
    private int _class;
    private String classDecoration;
    private int rivalCount;
    private int reverseRivalCount;
    private int maxStreak;
    private int rank;
    private int isRival;
    private int isReverseRival;

    public SolvedAcMember(UserSearchResponse response) {
        this.handle = response.getHandle();
        this.bio = response.getBio();
        this.organizations = Arrays.stream(response.getOrganizations()).map(Organization::new).toArray(Organization[]::new);
        if (response.getBadge() != null) {
            this.badge = new Badge(response.getBadge());
        }
        if (response.getBackground() != null) {
            this.background = new Background(response.getBackground());
        }
        this.profileImageUrl = response.getProfileImageUrl();
        this.solvedCount = response.getSolvedCount();
        this.voteCount = response.getVoteCount();
        this.exp = response.getExp();
        this.tier = response.getTier();
        this.rating = response.getRating();
        this.ratingByProblemsSum = response.getRatingByProblemsSum();
        this.ratingByClass = response.getRatingByClass();
        this.ratingBySolvedCount = response.getRatingBySolvedCount();
        this.ratingByVoteCount = response.getRatingByVoteCount();
        this._class = response.get_class();
        this.classDecoration = response.getClassDecoration();
        this.rivalCount = response.getRivalCount();
        this.reverseRivalCount = response.getReverseRivalCount();
        this.maxStreak = response.getMaxStreak();
        this.rank = response.getRank();
        this.isRival = response.getIsRival();
        this.isReverseRival = response.getIsReverseRival();
    }

    public class Organization {
        private int organizationId;
        private String name;
        private String type;
        private int rating;
        private int userCount;
        private int voteCount;
        private int solvedCount;
        private String color;

        public Organization(UserSearchResponse.Organization organization) {
            this.organizationId = organization.getOrganizationId();
            this.name = organization.getName();
            this.type = organization.getType();
            this.rating = organization.getRating();
            this.userCount = organization.getUserCount();
            this.voteCount = organization.getVoteCount();
            this.solvedCount = organization.getSolvedCount();
            this.color = organization.getColor();
        }
    }

    public class Badge {
        private String badgeId;
        private String badgeImageUrl;
        private String displayName;
        private String displayDescription;

        public Badge(UserSearchResponse.Badge badge) {
            this.badgeId = badge.getBadgeId();
            this.badgeImageUrl = badge.getBadgeImageUrl();
            this.displayName = badge.getDisplayName();
            this.displayDescription = badge.getDisplayDescription();
        }
    }

    public class Background {
        private String backgroundId;
        private String backgroundImageUrl;
        private String author;
        private String authorUrl;
        private String displayName;
        private String displayDescription;

        public Background(UserSearchResponse.Background background) {
            this.backgroundId = background.getBackgroundId();
            this.backgroundImageUrl = background.getBackgroundImageUrl();
            this.author = background.getAuthor();
            this.authorUrl = background.getAuthorUrl();
            this.displayName = background.getDisplayName();
            this.displayDescription = background.getDisplayDescription();
        }
    }
}
