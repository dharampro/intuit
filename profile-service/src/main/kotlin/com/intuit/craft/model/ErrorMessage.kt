package com.intuit.craft.model

data class ErrorMessage(
    val code: Int,
    val type: String,
    val uri: String,
    val message: String
)
