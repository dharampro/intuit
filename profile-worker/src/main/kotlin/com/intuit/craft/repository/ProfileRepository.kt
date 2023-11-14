package com.intuit.craft.repository

import com.intuit.craft.model.Profile
import org.springframework.data.mongodb.repository.MongoRepository

interface ProfileRepository : MongoRepository<Profile, String> {
}