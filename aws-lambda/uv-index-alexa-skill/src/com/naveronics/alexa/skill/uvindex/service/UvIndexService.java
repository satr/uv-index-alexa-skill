package com.naveronics.alexa.skill.uvindex.service;

import com.naveronics.alexa.skill.uvindex.common.Constant;

public class UvIndexService {
    public String getUvIndexLevelText() {
        return getUvIndexLevelText(1);//TODO - add uv-index provider data
    }

    public String getUvIndexLevelText(int index) {
        if(index < 3) {
            return Constant.UvIndexLevelText.LOW;
        } else if (index < 6) {
            return Constant.UvIndexLevelText.MODERATE;
        } else if (index < 8) {
            return Constant.UvIndexLevelText.HIGH;
        } else if (index < 11) {
            return Constant.UvIndexLevelText.VERY_HIGH;
        }
        return Constant.UvIndexLevelText.EXTREME;
    }
}
