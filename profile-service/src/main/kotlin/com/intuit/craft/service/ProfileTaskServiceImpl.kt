package com.intuit.craft.service

import com.intuit.craft.exception.Messages
import com.intuit.craft.exception.ProfileExistsException
import com.intuit.craft.exception.ProfileNotFoundException
import com.intuit.craft.model.KafkaEvent
import com.intuit.craft.model.ProfileTaskResponse
import com.intuit.craft.model.document.Profile
import com.intuit.craft.model.document.ProfileTask
import com.intuit.craft.model.enums.ProfileTaskType
import com.intuit.craft.repository.ProfileRepository
import com.intuit.craft.repository.ProfileTaskRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class ProfileTaskServiceImpl @Autowired constructor(
    private val profileTaskRepository: ProfileTaskRepository,
    private val profileRepository: ProfileRepository,
    private val kafkaTemplate: KafkaTemplate<String, KafkaEvent>
) : ProfileTaskService {

    @Value("\${business.profile.topic}")
    var topic: String = "profile_topic"

    val log = LoggerFactory.getLogger(this::class.java)

    override fun create(profile: Profile): ProfileTaskResponse {
        log.info("checking if profile already exists::{}", profile)
        val existingProfile = profileRepository.findByCompanyNameAndLegalName(profile.companyName, profile.legalName)
        if (existingProfile != null && existingProfile.get().equals(profile.get())) {
            log.error("profile exists with same data::{}", existingProfile)
            throw ProfileExistsException(Messages.PROFILE_EXISTS)
        }
        val taskResult = profileTaskRepository.save(ProfileTask.initTask(profile, ProfileTaskType.CREATE_PROFILE))
        log.info("event created for type::{}, and id::{} ", taskResult.profileTaskType, taskResult.id)
        return taskResult.let {
            val message = KafkaEvent(it.id!!, it.profileTaskType)
            log.info("sending kafka event message::{}", profile)
            kafkaTemplate.send(topic, it.id, message)
            ProfileTaskResponse(it.id, it.profileTaskType)
        }
    }

    override fun update(profile: Profile, profileId: String): ProfileTaskResponse {
        log.info("checking if profile already exists::{}", profile)
        val existingProfile = profileRepository.findByCompanyNameAndLegalName(profile.companyName, profile.legalName)
        if (existingProfile == null) {
            log.error("profile exists with same data::{}", existingProfile)
            throw ProfileNotFoundException(Messages.PROFILE_NOT_FOUND)
        }
        existingProfile.id?.let { profile.seId(it) }
        val taskResult = profileTaskRepository.save(ProfileTask.initTask(profile, ProfileTaskType.UPDATE_PROFILE))
        log.info("event created for type::{}, and id::{} ", taskResult.profileTaskType, taskResult.id)
        return taskResult.let {
            val message = KafkaEvent(it.id!!, it.profileTaskType)
            log.info("sending kafka event message::{}", profile)
            kafkaTemplate.send(topic, it.id, message)
            ProfileTaskResponse(it.id, it.profileTaskType)
        }
    }
}
