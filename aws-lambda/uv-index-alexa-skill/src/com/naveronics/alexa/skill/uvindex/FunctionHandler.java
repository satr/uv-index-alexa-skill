package com.naveronics.alexa.skill.uvindex;

import com.amazon.ask.Skill;
import com.amazon.ask.builder.StandardSkillBuilder;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.ResponseEnvelope;
import com.amazon.ask.util.JacksonSerializer;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.util.IOUtils;
import com.naveronics.alexa.skill.uvindex.request.handler.UvIndexRequestHandler;
import com.naveronics.alexa.skill.uvindex.service.UvIndexService;
import com.naveronics.alexa.skill.uvindex.service.UvIndexServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class FunctionHandler implements RequestStreamHandler {
    private final Skill skill;
    private final JacksonSerializer serializer;

    public FunctionHandler() {
        this(new UvIndexServiceImpl());
    }

    public FunctionHandler(UvIndexService uvIndexService) {
        skill = new StandardSkillBuilder()
                .addRequestHandler(new UvIndexRequestHandler(uvIndexService))
                .build();
        serializer = new JacksonSerializer();    }

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
        String request = IOUtils.toString(input);
        RequestEnvelope requestEnvelope = serializer.deserialize(request, RequestEnvelope.class);
        ResponseEnvelope responseEnvelope = skill.invoke(requestEnvelope);
        byte[] response = serializer.serialize(responseEnvelope).getBytes(StandardCharsets.UTF_8);
        output.write(response);
    }
}
