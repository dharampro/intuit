package com.intuit.craft.exception


open class ProfileException : RuntimeException {
    constructor(message: String) : super(message)
    constructor() : super()
}

class KafkaMessageExceptionable :
    ProfileException("Invalid kafka message")

class EventNotFountExceptionable(eventId: String) :
    ProfileException("ProfileEvent not found with id $eventId")

class KafkaMessageProcessorException() :
    ProfileException("Unpronounceable entity, event can not ve processes")