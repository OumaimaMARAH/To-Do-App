package com.example.Task.Feign;

import com.example.Task.Enumeration.ExceptionsMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Default;

import java.io.IOException;
import java.io.InputStream;

public class RetreiveMessageErrorDecoder implements ErrorDecoder {
    private ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        ExceptionsMessage message = null;
        try (InputStream bodyIs = response.body()
                .asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            message = mapper.readValue(bodyIs, ExceptionsMessage.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }
        switch (response.status()) {
            case 400:
                return new BadRequestException(message.getMessage() != null ? message.getMessage() : "Bad Request");
            case 404:
                return new NotFoundException(message.getMessage() != null ? message.getMessage() : "Not found");
            default:
                return errorDecoder.decode(methodKey, response);
        }
    }
}
