package com.intuit.craft.model.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.ZonedDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ErrorMessage(
    val error: String,
    val timeStamp: ZonedDateTime,
    val uri: String? = null,
    val fieldError: FieldError? = null
)
