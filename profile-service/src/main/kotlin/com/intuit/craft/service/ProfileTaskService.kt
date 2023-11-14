package com.intuit.craft.service

import com.intuit.craft.model.ProfileTaskResponse
import com.intuit.craft.model.document.Profile

interface ProfileTaskService {
    fun create(profile: Profile): ProfileTaskResponse

    fun update(profile: Profile, profileId: String): ProfileTaskResponse
}
