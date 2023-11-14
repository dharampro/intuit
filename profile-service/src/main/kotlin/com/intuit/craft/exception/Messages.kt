package com.intuit.craft.exception

import org.springframework.http.HttpStatus

enum class Messages(val code: HttpStatus, val message: String) {
    PROFILE_EXISTS(HttpStatus.CONFLICT, "profile.already.exists"),
    PROFILE_NOT_FOUND(HttpStatus.NOT_FOUND, "profile.not.found")

}
