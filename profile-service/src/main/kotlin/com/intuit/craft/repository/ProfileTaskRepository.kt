package com.intuit.craft.repository

import com.intuit.craft.model.document.ProfileTask
import org.springframework.data.mongodb.repository.MongoRepository

interface ProfileTaskRepository : MongoRepository<ProfileTask, String>
