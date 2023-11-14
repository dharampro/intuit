package com.intuit.craft.command

import com.intuit.craft.model.ProfileStatus
import com.intuit.craft.repository.ProfileStatusRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UpdateStatusCommand @Autowired constructor(
    private val profileStatusRepository: ProfileStatusRepository
) {
    fun execute(profileStatus: ProfileStatus): ProfileStatus {
        return profileStatusRepository.save(profileStatus)
    }
}