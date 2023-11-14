package com.intuit.craft.repository

import com.intuit.craft.model.ProfileStatus
import org.springframework.data.mongodb.repository.MongoRepository

interface ProfileStatusRepository : MongoRepository<ProfileStatus, String> {
}