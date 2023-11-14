package com.intuit.craft.model

import java.util.UUID
import org.springframework.data.mongodb.core.mapping.Document

@Document("profile")
data class Profile(
    val id: String? = null,
    val profileId: String? = UUID.randomUUID().toString(),
    val isProcessed: Boolean? = false,
    val companyName: String,
    val legalName: String,
    val email: String,
    val website: String,
    val businessProfileAddress: ProfileAddress,
    val legalProfileAddress: ProfileAddress
)
