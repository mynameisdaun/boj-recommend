package com.daun.word.global.infra.solvedac.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class UserSearchResponse {
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

    @Data
    @NoArgsConstructor
    public static class Organization {
        private int organizationId;
        private String name;
        private String type;
        private int rating;
        private int userCount;
        private int voteCount;
        private int solvedCount;
        private String color;
    }

    @Data
    @NoArgsConstructor
    public static class Badge {
        private String badgeId;
        private String badgeImageUrl;
        private String displayName;
        private String displayDescription;
    }

    @Data
    @NoArgsConstructor
    public static class Background {
        private String backgroundId;
        private String backgroundImageUrl;
        private String author;
        private String authorUrl;
        private String displayName;
        private String displayDescription;
    }
}
