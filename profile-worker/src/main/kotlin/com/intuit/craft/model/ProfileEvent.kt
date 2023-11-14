package com.intuit.craft.model

import com.intuit.craft.model.dto.EventType
import org.springframework.data.mongodb.core.mapping.Document

@Document("events")
data class ProfileEvent(
    val id: String? = null,
    val type: EventType,
    val payload: Profile? = null
)
