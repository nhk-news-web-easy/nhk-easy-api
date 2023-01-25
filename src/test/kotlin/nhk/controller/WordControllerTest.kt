package nhk.controller

import nhk.BaseTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class WordControllerTest : BaseTest() {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun shouldReturnBadRequest() {
        mockMvc.perform(MockMvcRequestBuilders.get("/words"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

    @Test
    fun shouldGetWords() {
        mockMvc.perform(MockMvcRequestBuilders.get("/words?newsId=1"))
                .andExpect(MockMvcResultMatchers.status().isOk)
    }
}
