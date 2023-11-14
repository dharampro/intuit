package com.intuit.craft.model

import org.springframework.data.mongodb.core.mapping.Document

@Document(collation = "subscription")
data class Subscription(
    val id: String,
    val productId: String,
    val profileId: String,
    val isActive: Boolean
)
