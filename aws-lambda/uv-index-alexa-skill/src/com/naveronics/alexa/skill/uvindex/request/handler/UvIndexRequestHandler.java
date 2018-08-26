package com.naveronics.alexa.skill.uvindex.request.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.naveronics.alexa.skill.uvindex.repository.ImageRepository;
import com.naveronics.alexa.skill.uvindex.service.UvIndexService;
import java.util.Optional;

public class UvIndexRequestHandler implements RequestHandler {
    private final ImageRepository imageRepository;
    private final UvIndexService uvIndexService;

    public UvIndexRequestHandler() {
        imageRepository = new ImageRepository();
        uvIndexService = new UvIndexService();
    }

    @Override
    public boolean canHandle(HandlerInput input) {
        return true;
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String uvIndexLevelText = uvIndexService.getUvIndexLevelText();
        String speechText = "UV index is " + uvIndexLevelText;
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("UV index", uvIndexLevelText)
//                .withStandardCard("UV index is ", uvIndexLevelText, imageRepository.getUvIndexLevelImageBy(number))
                .build();
    }
}