
# ChatGPT 기반 챗봇 프로젝트

이 프로젝트는 OpenAI의 ChatGPT API를 사용하여 Spring Boot로 구현한 간단한 챗봇입니다. 사용자 인터페이스는 HTML, Bootstrap을 사용해 구성되었으며, jQuery를 통해 실시간으로 사용자 메시지를 서버로 전송하고, ChatGPT로부터의 응답을 받아 대화형 챗봇 기능을 제공합니다.

## 주요 기능

- ChatGPT API를 사용하여 자연어 처리 및 응답 생성
- HTML, Bootstrap으로 만든 직관적인 웹 인터페이스
- jQuery를 사용하여 AJAX로 메시지 전송 및 실시간 응답 처리
- 엔터 키 또는 버튼 클릭을 통해 메시지를 전송 가능

## 기술 스택

- **Spring Boot**: 백엔드 애플리케이션
- **OpenAI ChatGPT API**: AI 응답 생성
- **JDK 17**
- **Gradle**: 의존성 관리
- **Lombok**: 코드 축약을 위한 애노테이션
- **JUnit 5**: 테스트 프레임워크
- **HTML, Bootstrap**: 프론트엔드 UI 구성
- **jQuery**: 클라이언트 사이드 로직 처리

## 디렉토리 구조

```
chatgpt-chatbot/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/
│   │   │       └── example/
│   │   │           ├── ChatGptChatbotApplication.java
│   │   │           ├── controller/
│   │   │           │   └── ChatGptController.java
│   │   │           └── service/
│   │   │               └── ChatGptService.java
│   │   ├── resources/
│   │   │   ├── application.properties
│   │   │   └── static/
│   │   │       └── index.html
│   └── test/
│       └── java/
│           └── org/
│               └── example
│                   ├── controller
│                   │   └── ChatGptControllerTest.java
│                   └── service
│                       └── ChatGptServiceTest.java
├── build.gradle
└── settings.gradle
```

## 실행 방법

1. **API Key 설정**
    - OpenAI API Key를 [OpenAI 계정](https://beta.openai.com/signup/)에서 발급받아야 합니다.
    - `ChatGptService.java` 파일에서 `API_KEY` 상수에 발급받은 API Key를 설정합니다.

2. **프로젝트 빌드 및 실행**
    - 프로젝트의 루트 디렉토리에서 Gradle을 사용해 빌드 및 실행합니다.

   ```bash
   ./gradlew bootRun
   ```

3. **웹 페이지 실행**
    - 웹 브라우저에서 `http://localhost:8080`으로 접속하면 챗봇 웹 페이지가 실행됩니다.

4. **챗봇 사용**
    - 메시지를 입력하고 전송 버튼을 클릭하거나 엔터 키를 누르면 ChatGPT와의 대화가 가능합니다.

## ChatGPT API 사용

### 엔드포인트
- 최신 OpenAI API에서는 **https://api.openai.com/v1/chat/completions** 엔드포인트를 사용합니다.
- 요청 형식은 다음과 같습니다:

  ```json
  {
    "model": "gpt-3.5-turbo",
    "messages": [
      {"role": "user", "content": "Hello!"}
    ],
    "max_tokens": 100
  }
  ```

### `max_tokens` 설명
- `max_tokens`는 ChatGPT가 생성할 응답의 **최대 토큰 수**를 제한합니다.
- 토큰은 단어의 일부, 단어 전체, 구두점 등을 포함하는 기본 단위입니다.
- 예를 들어, `max_tokens = 100`은 대략 75~100개의 단어 또는 400~600개의 글자로 응답을 제한할 수 있습니다.

## 문제 해결

- **404 오류 (Unexpected code Response)**
    - API URL이 `https://api.openai.com/v1/completions`로 설정된 경우 404 오류가 발생할 수 있습니다.` 최신 엔드포인트인 https://api.openai.com/v1/chat/completions로 수정하세요.

- **API Key 오류**
    - 유효하지 않은 API Key 사용 시 401 또는 403 오류가 발생할 수 있습니다. OpenAI 계정에서 API Key를 발급받고 설정을 다시 확인하세요.

## 참고 자료

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [OpenAI API Documentation](https://beta.openai.com/docs/)
- [Bootstrap Documentation](https://getbootstrap.com/)
- [jQuery Documentation](https://jquery.com/)
