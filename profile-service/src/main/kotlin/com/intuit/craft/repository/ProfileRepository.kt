package com.intuit.craft.repository

import com.intuit.craft.model.document.Profile
import org.springframework.data.mongodb.repository.MongoRepository

interface ProfileRepository : MongoRepository<Profile, String> {
    fun findByCompanyNameAndLegalName(companyName: String, legalName: String): Profile?
}
