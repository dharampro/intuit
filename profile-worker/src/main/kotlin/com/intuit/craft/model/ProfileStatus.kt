package com.intuit.craft.model

import com.intuit.craft.model.dto.EventType
import com.intuit.craft.model.dto.StatusType
import org.springframework.data.mongodb.core.mapping.Document

@Document("status")
data class ProfileStatus(
    val id: String? = null,
    val eventType: EventType,
    val status: StatusType,
    val response: String? = null,
    val error: String? = null
)
