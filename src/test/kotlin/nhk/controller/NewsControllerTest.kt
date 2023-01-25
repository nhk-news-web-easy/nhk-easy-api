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
        mockMvc.perform(MockMvcRequestBuilders.get("/news?startDate=2018-04-04T16:00:00.000Z&endDate=2018-03-04T16:00:00.000Z"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
        mockMvc.perform(MockMvcRequestBuilders.get("/news?startDate=2018-04-04T16:00:00.000Z&endDate=2018-06-04T16:00:00.000Z"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

    @Test
    fun shouldReturnNews() {
        mockMvc.perform(MockMvcRequestBuilders.get("/news?startDate=2018-04-04T16:00:00.000Z&endDate=2018-04-10T16:00:00.000Z"))
                .andExpect(MockMvcResultMatchers.status().isOk)
    }
}
