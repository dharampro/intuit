package com.intuit.craft.model

import com.intuit.craft.model.enums.ProfileTaskType

data class KafkaEvent(
    val id: String,
    val type: ProfileTaskType
)
