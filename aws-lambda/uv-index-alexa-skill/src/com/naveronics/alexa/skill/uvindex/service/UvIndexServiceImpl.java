package com.naveronics.alexa.skill.uvindex.service;

import com.naveronics.alexa.skill.uvindex.common.Constant;
import com.naveronics.alexa.skill.uvindex.helper.UvIndexMessageBuilder;
import com.naveronics.alexa.skill.uvindex.repository.UvIndexRepository;
import com.naveronics.alexa.skill.uvindex.repository.UvIndexRepositoryImpl;

public class UvIndexServiceImpl implements UvIndexService {

    private UvIndexRepository uvIndexRepository;

    public UvIndexServiceImpl() {
        this(new UvIndexRepositoryImpl());
    }

    public UvIndexServiceImpl(UvIndexRepository uvIndexRepository) {
        this.uvIndexRepository = uvIndexRepository;
    }

    @Override
    public String getUvIndexLevelText(){
        int uvIndex = uvIndexRepository.get();
        if(uvIndex < 3) {
            return Constant.UvIndexLevelText.LOW;
        } else if (uvIndex < 6) {
            return Constant.UvIndexLevelText.MODERATE;
        } else if (uvIndex < 8) {
            return Constant.UvIndexLevelText.HIGH;
        } else if (uvIndex < 11) {
            return Constant.UvIndexLevelText.VERY_HIGH;
        }
        return Constant.UvIndexLevelText.EXTREME;
    }

    @Override
    public String getUvIndexLevelDescription() {
        return UvIndexMessageBuilder.getUvLevelDescription(getUvIndexLevelText());
    }
}
