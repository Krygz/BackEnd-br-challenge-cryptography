package com.test.cryptography.dto;

import javax.validation.constraints.NotNull;


public record EncoderResponse(
       @NotNull Long id,
       @NotNull(message = "This field is required")  String userDocument,
       @NotNull(message = "This field is required")  String creditCardToken,
       @NotNull(message = "This field is required")  Long amount) {
}
