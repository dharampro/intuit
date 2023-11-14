package com.intuit.craft.model.document

import com.intuit.craft.common.enums.TaskResult
import com.intuit.craft.common.enums.TaskState
import com.intuit.craft.model.TaskStatus
import com.intuit.craft.model.enums.ProfileTaskType
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "profileTask")
data class ProfileTask(
    val id: String? = null,
    val profileTaskType: ProfileTaskType,
    val payload: Profile,
    val taskStatus: TaskStatus
) {
    companion object {
        fun initTask(payload: Profile, profileTaskType: ProfileTaskType) = ProfileTask(
            profileTaskType = profileTaskType,
            payload = payload,
            taskStatus = TaskStatus(
                state = TaskState.INIT,
                result = TaskResult.INIT
            )
        )
    }
}
