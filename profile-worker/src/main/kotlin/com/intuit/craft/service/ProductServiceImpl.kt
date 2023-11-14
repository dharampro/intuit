package com.intuit.craft.service

import com.intuit.craft.model.Subscription
import com.intuit.craft.repository.SubscriptionRepository
import org.springframework.beans.factory.annotation.Autowired

class ProductServiceImpl @Autowired constructor(
    private val subscriptionRepository: SubscriptionRepository
) : ProductService {

    override fun validate(productId: String, profileId: String): Subscription {
        return subscriptionRepository.findByProductIdAndProfileId(productId, profileId)
    }

    override fun createSubscription(subscriptions: List<Subscription>): List<Subscription> {
        return subscriptionRepository.saveAll(subscriptions)
    }
}