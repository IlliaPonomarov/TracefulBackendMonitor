package com.tracer.logger.json.serialization;

import com.fasterxml.jackson.databind.JsonSerializer;
import org.springframework.http.HttpMethod;


public class HttpMethodSerializer extends JsonSerializer<HttpMethod> {
    @Override
    public void serialize(HttpMethod httpMethod, com.fasterxml.jackson.core.JsonGenerator jsonGenerator, com.fasterxml.jackson.databind.SerializerProvider serializerProvider) throws java.io.IOException {
        jsonGenerator.writeString(httpMethod.name());
    }
}
