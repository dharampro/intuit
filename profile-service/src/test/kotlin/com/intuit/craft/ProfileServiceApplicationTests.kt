package com.intuit.craft

import org.junit.jupiter.api.Test
import org.junit.platform.suite.api.SelectPackages
import org.junit.platform.suite.api.Suite
import org.springframework.boot.test.context.SpringBootTest

@Suite
@SelectPackages("com.intuit.craft")
@SpringBootTest
class ProfileServiceApplicationTests {
    @Test
    fun contextLoads() {
    }
}
