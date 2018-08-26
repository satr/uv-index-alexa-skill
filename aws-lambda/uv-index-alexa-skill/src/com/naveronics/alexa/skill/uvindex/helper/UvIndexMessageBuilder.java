package com.naveronics.alexa.skill.uvindex.helper;

public final class UvIndexMessageBuilder {
    public static String getUvLevelDescription(String uvIndexLevelText) {
        return  "UV index is " + uvIndexLevelText;
    }

    public static String getTitle() {
        return "UV index";
    }
}
