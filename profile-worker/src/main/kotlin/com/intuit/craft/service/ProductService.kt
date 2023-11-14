package com.intuit.craft.service

import com.intuit.craft.model.Subscription

interface ProductService {
    fun validate(productId: String, profileId: String): Subscription
    fun createSubscription(subscriptions: List<Subscription>): List<Subscription>
}