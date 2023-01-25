package nhk.controller

import nhk.BaseTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class NewsControllerTest : BaseTest() {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun shouldReturnBadRequest() {
        mockMvc.perform(MockMvcRequestBuilders.get("/news"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
    }
}
