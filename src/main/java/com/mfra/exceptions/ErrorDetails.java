package com.mfra.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public final class ErrorDetails {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private final Date timestamp;
    private final String message;
    private final String request;
}
