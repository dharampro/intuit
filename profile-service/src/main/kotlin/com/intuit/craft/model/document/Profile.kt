package com.intuit.craft.model.document

import com.intuit.craft.model.ProfileAddress
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "profile")
data class Profile(
    val id: String? = null,
    val companyName: String,
    val legalName: String,
    val businessAddress: ProfileAddress,
    val legalAddress: ProfileAddress,
    val taxIdentifier: String,
    val email: String,
    val website: String,
    val isActive: Boolean = false
) {
    fun get() = copy(
        id = null
    )

    fun seId(profileId: String) = copy(
        id = profileId
    )
}
