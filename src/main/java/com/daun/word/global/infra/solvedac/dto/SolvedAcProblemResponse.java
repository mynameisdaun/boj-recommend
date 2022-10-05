package com.daun.word.global.infra.solvedac.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SolvedAcProblemResponse {
    private int problemId;
    private String titleKo;
    private List<Title> titles;
    private boolean isSolvable;
    private boolean isPartial;
    private int acceptedUserCount;
    private int level;
    private int votedUserCount;
    private boolean sprout;
    private boolean givesNoRating;
    private boolean isLevelLocked;
    private double averageTries;
    private boolean official;
    private List<Tag> tags;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Tag {
        private String key;
        private boolean isMeta;
        private int bojTagId;
        private int problemCount;
        private List<DisplayName> displayNames;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DisplayName {
        private String language;
        private String name;
        private String short_name;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Title {
        private String language;
        private String languageDisplayName;
        private String title;
        private boolean isOriginal;
    }

}



