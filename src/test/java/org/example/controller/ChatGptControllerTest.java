package org.example.controller;

import org.example.service.ChatGptService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChatGptControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ChatGptService chatGptService;

    @InjectMocks
    private ChatGptController chatGptController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(chatGptController).build();
    }

    @Test
    public void testSendMessage_Success() throws Exception {
        // Mock 설정
        when(chatGptService.askChatGpt("Hello")).thenReturn("[{text=Hello}]");

        // 요청 데이터 준비
        Map<String, String> requestData = new HashMap<>();
        requestData.put("message", "Hello");

        // POST 요청을 통해 컨트롤러 테스트
        mockMvc.perform(post("/chat/send")
                        .contentType("application/json")
                        .content("{\"message\": \"Hello\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("[{text=Hello}]"));
    }

    @Test
    public void testSendMessage_Error() throws Exception {
        // Mock 설정
        when(chatGptService.askChatGpt("Hello")).thenThrow(new IOException("API Error"));

        // POST 요청을 통해 에러 처리 테스트
        mockMvc.perform(post("/chat/send")
                        .contentType("application/json")
                        .content("{\"message\": \"Hello\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Error: API Error"));
    }
}
