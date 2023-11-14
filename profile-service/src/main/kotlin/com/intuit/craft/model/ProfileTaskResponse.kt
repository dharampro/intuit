package com.intuit.craft.model

import com.intuit.craft.model.enums.ProfileTaskType

data class ProfileTaskResponse(
    val id: String,
    val taskType: ProfileTaskType
)
