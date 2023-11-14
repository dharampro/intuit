package com.intuit.craft.repository

import com.intuit.craft.model.Subscription
import org.springframework.data.mongodb.repository.MongoRepository

interface SubscriptionRepository : MongoRepository<Subscription, String> {
    fun findByProductIdAndProfileId(productId: String, profileId: String): Subscription
}