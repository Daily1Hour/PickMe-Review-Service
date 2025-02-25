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
| GET    | /review/apiTest          | Authorization:<br> Bearer \<token>    |                                                               |                                                                             | 200: 성공 <br> 400: 잘못된 요청 <br> 401: 권한 없음 |


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

## 🖧 배치 다이어그램


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