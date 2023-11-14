package com.intuit.craft.handler

import com.intuit.craft.command.CreateProfileCommand
import com.intuit.craft.command.FetchEventCommand
import com.intuit.craft.command.UpdateStatusCommand
import com.intuit.craft.exception.EventNotFountExceptionable
import com.intuit.craft.exception.KafkaMessageExceptionable
import com.intuit.craft.exception.ProfileException
import com.intuit.craft.model.Profile
import com.intuit.craft.model.ProfileStatus
import com.intuit.craft.model.dto.EventType
import com.intuit.craft.model.dto.KafkaEvent
import com.intuit.craft.model.dto.StatusType
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class ProfileHandler @Autowired constructor(
    private val fetchEventCommand: FetchEventCommand,
    private val createProfileCommand: CreateProfileCommand,
    private val updateStatusCommand: UpdateStatusCommand
) {
    val log: Logger = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topics = ["profile_events"], groupId = "profile_group")
    fun process(record: ConsumerRecord<String, KafkaEvent>?) = try {
        if (record == null) {
            log.error("Invalid message received")
            throw KafkaMessageExceptionable()
        }
        val response = fetchEventCommand.execute(record.value().id)
        if (response.isPresent && response.get().payload == null) {
            log.error("Message [ Key:: ${record.key()}, value :: ${record.value()}]")
            throw EventNotFountExceptionable(record.value().id)
        }
        when (record.value().type) {
            EventType.CREATE -> createProfile(record, response.get().payload!!)
            EventType.UPDATE -> updateProfile(record, response.get().payload!!)
        }
    } catch (exception: ProfileException) {
        log.error("Error occurred while processing command", exception)
        errorUpdate(record, exception)
    }

    private fun createProfile(record: ConsumerRecord<String, KafkaEvent>?, profile: Profile) {
        log.info("Creating profile for record {}", record)
        val result = createProfileCommand.execute(profile)
        log.info("Profile created successfully {}", result)
        updateStatusCommand.execute(
            ProfileStatus(
                id = record?.key(),
                eventType = record?.value()?.type!!,
                status = StatusType.SUCCESS,
                response = result.profileId
            )
        )
    }

    private fun updateProfile(record: ConsumerRecord<String, KafkaEvent>?, profile: Profile) {
        log.info("Creating profile for record {}", record)
        val result = createProfileCommand.execute(profile)
        log.info("Profile created successfully {}", result)
        updateStatusCommand.execute(
            ProfileStatus(
                id = record?.key(),
                eventType = record?.value()?.type!!,
                status = StatusType.SUCCESS,
                response = result.profileId
            )
        )
    }

    private fun errorUpdate(record: ConsumerRecord<String, KafkaEvent>?, exception: ProfileException) {
        log.info("Ërror occurred while processing event {}", record, exception)
        updateStatusCommand.execute(
            ProfileStatus(
                id = record?.key(),
                eventType = record?.value()?.type!!,
                status = StatusType.ERROR,
                error = exception.message
            )
        )
    }

    private fun validateProfile(profileId: String, productId: String) {
        val url = "/v1/craft/validate"
    }


}