package com.intuit.craft.model.dto

data class KafkaEvent(
    val id: String,
    val type: EventType
)
