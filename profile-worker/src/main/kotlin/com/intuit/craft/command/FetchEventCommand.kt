package com.intuit.craft.command

import com.intuit.craft.model.ProfileEvent
import com.intuit.craft.repository.ProfileEventRepository
import java.util.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FetchEventCommand @Autowired constructor(
    private val profileEventRepository: ProfileEventRepository
) {
    fun execute(event: String): Optional<ProfileEvent> {
        return profileEventRepository.findById(event)
    }
}