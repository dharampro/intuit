package com.intuit.craft.controller

import com.intuit.craft.model.ProfileTaskResponse
import com.intuit.craft.model.document.Profile
import com.intuit.craft.service.ProfileTaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/craft")
class ProfileTaskController @Autowired constructor(
    private val profileTaskService: ProfileTaskService
) {
    @PostMapping("/v1/profile")
    fun create(@RequestBody profile: Profile): ResponseEntity<ProfileTaskResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(profileTaskService.create(profile))
    }

    @PutMapping("/v1/profile/{profileId}")
    fun update(@RequestBody profile: Profile, @PathVariable profileId: String): ResponseEntity<ProfileTaskResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(profileTaskService.update(profile, profileId))
    }
}
