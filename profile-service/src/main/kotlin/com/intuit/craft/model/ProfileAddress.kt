package com.intuit.craft.model

data class ProfileAddress(
    val addressLine: String,
    val city: String,
    val state: String,
    val country: String,
    val zipCode: Int
)
