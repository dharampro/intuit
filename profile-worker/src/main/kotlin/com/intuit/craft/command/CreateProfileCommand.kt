package com.intuit.craft.command

import com.intuit.craft.model.Profile
import com.intuit.craft.repository.ProfileRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CreateProfileCommand @Autowired constructor(
    private val profileRepository: ProfileRepository
) {

    fun execute(profile: Profile): Profile {
        return profileRepository.save(profile)
    }
}