package com.intuit.craft.model

import com.intuit.craft.common.enums.TaskResult
import com.intuit.craft.common.enums.TaskState

data class TaskStatus(
    val state: TaskState,
    val result: TaskResult
)
