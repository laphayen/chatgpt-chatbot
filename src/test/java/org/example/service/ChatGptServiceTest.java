package org.example.service;

import okhttp3.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ChatGptServiceTest {

    @Mock
    private OkHttpClient client;

    @Mock
    private Call call;

    @InjectMocks
    private ChatGptService chatGptService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAskChatGpt_Success() throws IOException {
        // 준비: Mock 응답 설정
        Response mockResponse = new Response.Builder()
                .request(new Request.Builder().url("https://api.openai.com/v1/completions").build())
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("OK")
                .body(ResponseBody.create("{\"choices\":[{\"text\":\"Hello\"}]}", MediaType.get("application/json")))
                .build();

        when(client.newCall(any(Request.class))).thenReturn(call);
        when(call.execute()).thenReturn(mockResponse);

        // 실행
        String result = chatGptService.askChatGpt("Hello");

        // 검증
        assertEquals("Hello! How can I help you today?", result);
        verify(client, times(1)).newCall(any(Request.class));
    }

}
