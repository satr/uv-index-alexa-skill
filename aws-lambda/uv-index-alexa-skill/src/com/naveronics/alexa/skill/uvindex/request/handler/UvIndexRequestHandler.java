package com.naveronics.alexa.skill.uvindex.request.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.naveronics.alexa.skill.uvindex.helper.UvIndexMessageBuilder;
import com.naveronics.alexa.skill.uvindex.repository.ImageRepository;
import com.naveronics.alexa.skill.uvindex.service.UvIndexService;

import java.util.Optional;

public class UvIndexRequestHandler implements RequestHandler {
    private final ImageRepository imageRepository;
    private final UvIndexService uvIndexService;

    public UvIndexRequestHandler(UvIndexService uvIndexService) {
        imageRepository = new ImageRepository();
        this.uvIndexService = uvIndexService;
    }

    @Override
    public boolean canHandle(HandlerInput input) {
        return true;
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String uvIndexLevelDescription = uvIndexService.getUvIndexLevelDescription();
        return input.getResponseBuilder()
                .withSpeech(uvIndexLevelDescription)
                .withSimpleCard(UvIndexMessageBuilder.getTitle(), uvIndexService.getUvIndexLevelText())
//                .withStandardCard(uvIndexLevelDescription, imageRepository.getUvIndexLevelImageBy(number))
                .build();
    }
}