package com.intuit.craft.common.enums

enum class TaskState(val value: Int) {
    INIT(0),
    IN_PROGRESS(1),
    COMPLETED(2),
    FAILED(-1)
}
