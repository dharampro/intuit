package com.intuit.craft.exception

open class ProfileServiceException : RuntimeException {
    constructor(message: String) : super(message)
    constructor() : super()
}

class ProfileExistsException(val msg: Messages) :
    ProfileServiceException()

class ProfileNotFoundException(val msg: Messages) :
    ProfileServiceException()