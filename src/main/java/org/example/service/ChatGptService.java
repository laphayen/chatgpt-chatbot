package org.example.service;

import okhttp3.*;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatGptService {

    private static final String API_KEY = "";
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";  // 수정된 URL

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String askChatGpt(String prompt) throws IOException {
        // 요청 바디 생성
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");  // 사용할 모델
        requestBody.put("messages", new Object[]{
                new HashMap<String, String>() {{
                    put("role", "user");
                    put("content", prompt);
                }}
        });
        requestBody.put("max_tokens", 200);

        String jsonBody = objectMapper.writeValueAsString(requestBody);

        // 요청 빌드
        Request request = new Request.Builder()
                .url(API_URL)
                .post(RequestBody.create(jsonBody, MediaType.get("application/json")))
                .addHeader("Authorization", "Bearer " + API_KEY)
                .build();

        // 요청 전송 및 응답 처리
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String responseBody = response.body().string();
            // 응답 바디를 JSON으로 변환하고 필요한 값을 추출
            Map<String, Object> responseMap = objectMapper.readValue(responseBody, Map.class);
            Map<String, Object> choice = (Map<String, Object>) ((List<Object>) responseMap.get("choices")).get(0);
            Map<String, Object> message = (Map<String, Object>) choice.get("message");
            return message.get("content").toString();
        }
    }
}
