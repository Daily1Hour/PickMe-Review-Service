# 면접 회고(리뷰) 서비스

> API를 통해 사용자가 면접 회고(리뷰)를 관리할 수 있도록 지원하는 서비스

## 🛠️ 기술 스택

![Java](https://img.shields.io/badge/Java-007396?style=flat&logo=Java&logoColor=white)
![SpringBoot](https://img.shields.io/badge/SpringBoot-6DB33F?style=flat&logo=springboot&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-47A248?style=flat&logo=MongoDB&logoColor=white)  
![Docker](https://img.shields.io/badge/Docker-2496ED?style=flat&logo=Docker&logoColor=white)

## 📄 API 명세서

[![Swagger](https://img.shields.io/badge/Swagger-Green?style=flat&logo=swagger&logoColor=white)](https://daily1hour.github.io/PickMe-Review-Service/)

| Method | URI                     | Request Header                        | Query String                                                   | Request Body                                                                 | Code                                                |
|--------|-------------------------|---------------------------------------|---------------------------------------------------------------|-----------------------------------------------------------------------------|-----------------------------------------------------|
| POST   | /review/interview        | Authorization:<br> Bearer \<token>    |                                                               | PostInterviewReviewsDTO | 201: 성공 <br> 400: 잘못된 요청 <br> 401: 권한 없음 <br> 404: 면접 리뷰 없음 |
| GET    | /review/interview        | Authorization:<br> Bearer \<token>    | reviewId: string                                              |                                                                             | 200: 성공 <br> 400: 잘못된 요청 <br> 401: 권한 없음 <br> 404: 면접 리뷰 없음 |
| DELETE | /review/interview        | Authorization:<br> Bearer \<token>    | reviewId: string                                               |                                                                             | 200: 성공 <br> 400: 잘못된 요청 <br> 401: 권한 없음 <br> 404: 면접 리뷰 없음 |
| PUT    | /review/interview        | Authorization:<br> Bearer \<token>    | reviewId: string                                               | PutInterviewReviewsDTO     | 200: 성공 <br> 400: 잘못된 요청 <br> 401: 권한 없음 <br> 404: 면접 리뷰 없음 |

## 📊 다이어그램

### 🔹 유즈케이스 다이어그램
![Image](https://github.com/user-attachments/assets/273ed3b6-6846-4984-85c8-b54774366811)
>유즈케이스 다이어그램은 면접 회고 작성 기능을 중심으로, 사용자가 각 섹션별로 어떤 정보를 기록할 수 있는지를 나타냅니다.
면접 회고는 총 7개의 세부 항목(사전 준비, 면접 진행 과정, 질문 및 답변, 기술적 평가, 커뮤니케이션, 면접 후 분석, 다음 면접 준비)으로 구성되며, 사용자는 회고를 작성, 수정, 삭제할 수 있습니다.
### 🔀 데이터 흐름 다이어그램
![Image](https://github.com/user-attachments/assets/17994f3b-ecdd-4fc1-91e2-256d583b4302)
>다이어그램은 면접 회고 서비스의 데이터 흐름을 나타낸 것입니다.
사용자의 이벤트 발생(조회, 저장, 수정, 삭제)은 프론트엔드(React)를 통해 REST API로 전달되며, Spring Boot 서버는 JWT 기반 인증 후 MongoDB와 연동하여 데이터를 처리합니다. 처리 결과는 다시 사용자에게 반환됩니다.

## 🚀 실행 방법

### 도커환경

```sh
# build
$ docker build -t my-image .

# run
$ docker run --env-file .env -p 8080:8080 my-image:latest
```

### 로컬환경

```sh
# Gradle 빌드 수행 (테스트 제외하고 빌드)
$ ./gradlew clean build -x test --no-daemon

# 빌드된 JAR 파일을 실행
$ java -jar review-0.0.1-SNAPSHOT.jar
```

## 📂 폴더 구조

> Layered Archictecture

```python
review
 ┣ .github
 ┃ ┣ rulesets
 ┃ ┃ ┗ Main-Rule.json
 ┃ ┣ workflows
 ┃ ┃ ┣ auto-assign.yml
 ┃ ┃ ┣ generate-swagger.yml
 ┃ ┃ ┗ gradle-build.yml
 ┃ ┣ auto-assign-config.yml
 ┃ ┗ swagger-index.html
 ┣ src
 ┃ ┣ main
 ┃ ┃ ┣ java
 ┃ ┃ ┃ ┗ com
 ┃ ┃ ┃ ┃ ┗ pickme
 ┃ ┃ ┃ ┃ ┃ ┗ review
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ config
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ security
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ JWTInterceptor.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ SwaggerConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ WebClientConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ WebConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ ReviewController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ get
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ GetCommunicationDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ GetInterviewAnalysisDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ GetInterviewDetailDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ GetInterviewProcessDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ GetInterviewReviewsDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ GetNextPreparationDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ GetPreparationDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ GetQuestionsAnswersDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ GetReviewDetailDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ GetReviewDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ post
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ PostCommunicationDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ PostInterviewAnalysisDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ PostInterviewDetailDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ PostInterviewProcessDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ PostInterviewReviewsDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ PostNextPreparationDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ PostPreparationDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ PostQuestionsAnswersDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ PostReviewDetailDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ put
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ PutCommunicationDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ PutInterviewAnalysisDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ PutInterviewDetailDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ PutInterviewProcessDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ PutInterviewReviewsDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ PutNextPreparationDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ PutPreparationDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ PutQuestionsAnswersDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ PutReviewDetailDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ Review.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ ReviewMongoQueryProcessor.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ ReviewRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ mapper
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ ReviewMapper.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ ExternalApiService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ JWTService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ ReviewService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ ReviewApplication.java
 ┃ ┃ ┗ resources
 ┃ ┃ ┃ ┣ static
 ┃ ┃ ┃ ┣ templates
 ┃ ┃ ┃ ┗ application.properties
 ┃ ┣ test
 ┃ ┃ ┗ java
 ┃ ┃ ┃ ┗ com
 ┃ ┃ ┃ ┃ ┗ pickme
 ┃ ┃ ┃ ┃ ┃ ┗ review
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ ReviewApplicationTests.java
 ┃ ┗ .gitkeep
 ┣ .env
 ┣ .gitattributes
 ┣ .gitconfig
 ┣ .gitignore
 ┣ .gitmessage
 ┣ build.gradle
 ┣ Dockerfile
 ┣ gradlew
 ┣ gradlew.bat
 ┣ HELP.md
 ┣ review.iml
 ┣ settings.gradle
 ┣ setup.ps1
 ┗ setup.zsh
```