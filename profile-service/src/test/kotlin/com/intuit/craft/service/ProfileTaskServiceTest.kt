package com.intuit.craft.service

import com.intuit.craft.common.Objects
import com.intuit.craft.exception.Messages
import com.intuit.craft.exception.ProfileExistsException
import com.intuit.craft.model.KafkaEvent
import com.intuit.craft.repository.ProfileRepository
import com.intuit.craft.repository.ProfileTaskRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.any
import org.mockito.BDDMockito.anyString
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.test.context.EmbeddedKafka
import java.util.UUID

@ExtendWith(MockitoExtension::class)
@EmbeddedKafka(partitions = 1, brokerProperties = ["listeners=PLAINTEXT://localhost:9093", "port=9093"])
class ProfileTaskServiceTest {

    val profileRepository: ProfileRepository = mock()
    val profileTaskRepository: ProfileTaskRepository = mock()
    val kafkaTemplate: KafkaTemplate<String, KafkaEvent> = mock()

    val profileTaskService: ProfileTaskService = ProfileTaskServiceImpl(
        profileTaskRepository = profileTaskRepository,
        profileRepository = profileRepository,
        kafkaTemplate = kafkaTemplate
    )

    @Test
    fun `create profile success test`() {
        given(profileRepository.findByCompanyNameAndLegalName(anyString(), anyString()))
            .willReturn(null)
        given(profileTaskRepository.save(any()))
            .willReturn(Objects.getProfileTaskObject())
        val response = profileTaskService.create(Objects.getProfileObject(UUID.randomUUID().toString()))
        Assertions.assertEquals(response, Objects.profileResponseObject())
    }

    @Test
    fun `create profile bad request test`() {
        val profileId = UUID.randomUUID().toString()
        given(profileRepository.findByCompanyNameAndLegalName(anyString(), anyString()))
            .willReturn(Objects.getProfileObject(profileId))
        given(profileTaskRepository.save(any()))
            .willReturn(Objects.getProfileTaskObject())
        val exception: ProfileExistsException = Assertions.assertThrows(ProfileExistsException::class.java) {
            profileTaskService.create(Objects.getProfileObject(profileId))
        }
        Assertions.assertEquals(exception.msg, Messages.PROFILE_EXISTS)
    }
}
