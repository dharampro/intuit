package com.intuit.craft.repository

import com.intuit.craft.model.ProfileEvent
import org.springframework.data.mongodb.repository.MongoRepository

interface ProfileEventRepository : MongoRepository<ProfileEvent, String> {
}