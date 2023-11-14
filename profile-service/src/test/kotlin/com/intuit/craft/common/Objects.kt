package com.intuit.craft.common

import com.intuit.craft.common.enums.TaskResult
import com.intuit.craft.common.enums.TaskState
import com.intuit.craft.model.ProfileAddress
import com.intuit.craft.model.ProfileTaskResponse
import com.intuit.craft.model.TaskStatus
import com.intuit.craft.model.document.Profile
import com.intuit.craft.model.document.ProfileTask
import com.intuit.craft.model.enums.ProfileTaskType
import java.util.UUID

object Objects {

    private object Field {
        val ID = UUID.randomUUID().toString()
    }

    fun getProfileObject(id: String?) = Profile(
        id = id ?: Field.ID,
        companyName = "abc.io",
        legalName = "Abc pvt. ltd.",
        taxIdentifier = "PIN88765AAA01",
        email = "company@abc.com",
        website = "www.abc.com",
        legalAddress = ProfileAddress(
            addressLine = "Embassy Tech Village",
            city = "Bangalore",
            state = "KR",
            country = "IN",
            zipCode = 50001
        ),
        businessAddress = ProfileAddress(
            addressLine = "Embassy Tech Village",
            city = "Bangalore",
            state = "KR",
            country = "IN",
            zipCode = 50001
        )
    )

    fun getProfileTaskObject() = ProfileTask(
        id = Field.ID,
        profileTaskType = ProfileTaskType.CREATE_PROFILE,
        payload = getProfileObject(Field.ID),
        taskStatus = TaskStatus(
            state = TaskState.INIT,
            result = TaskResult.INIT
        )
    )

    fun profileResponseObject() = ProfileTaskResponse(
        id = Field.ID,
        taskType = ProfileTaskType.CREATE_PROFILE
    )
}
