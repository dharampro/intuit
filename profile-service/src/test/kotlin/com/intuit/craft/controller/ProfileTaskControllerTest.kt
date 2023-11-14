package com.intuit.craft.controller

import com.intuit.craft.common.Objects
import com.intuit.craft.exception.ErrorTypes
import com.intuit.craft.exception.Messages
import com.intuit.craft.exception.ProfileExistsException
import com.intuit.craft.model.ProfileTaskResponse
import com.intuit.craft.model.ErrorMessage
import com.intuit.craft.service.ProfileTaskService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.UUID

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension::class)
class ProfileTaskControllerTest @Autowired constructor(
    private val mockMvc: MockMvc
) {
    final val profileTaskService: ProfileTaskService = mock()

    @Test
    fun `create profile success test`() {
        val uri = "/craft/v1/profile"
        val request = Objects.getProfileObject(UUID.randomUUID().toString())
        given(profileTaskService.create(Objects.getProfileObject(UUID.randomUUID().toString())))
            .willReturn(Objects.profileResponseObject())
        mockMvc.post(uri, request, ProfileTaskResponse::class.java)
            .andExpect { status().is2xxSuccessful }
            .andExpect { content().toString().contains(request.id.toString()) }
    }

    @Test
    fun `create profile bad request test`() {
        val uri = "/craft/v1/profile"
        val request = Objects.getProfileObject(UUID.randomUUID().toString())
        val msg = "Profile with same data already exists."
        given(profileTaskService.create(Objects.getProfileObject(UUID.randomUUID().toString())))
            .willThrow(ProfileExistsException(Messages.PROFILE_EXISTS))
        mockMvc.post(uri, request, ProfileTaskResponse::class.java)
            .andExpect { status().isBadRequest }
            .andDo { print(content().toString()) }
            .andExpect {
                content().toString().contains(
                    ErrorMessage(HttpStatus.BAD_REQUEST.value(), ErrorTypes.BAD_REQUEST.value, uri, msg).toString()
                )
            }
    }
}
