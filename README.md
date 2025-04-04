
# ✨ showU - 자랑 서비스
> 일상 속 특별한 순간을 자랑하는 공간을 제공합니다.

<br>

## 📌 프로젝트 개요
> 회원이 자신의 일상을 담은 사진을 자랑하며, 다양한 사람과 소통하고 트렌디한 콘텐츠를 공유하는 웹 애플리케이션입니다.
> 
> [📄 발표 자료](https://github.com/Cloud-Engineering2/project2-team4/blob/main/2%EC%B0%A8%204%ED%8C%80%20%EB%B0%9C%ED%91%9C%20%EC%9E%90%EB%A3%8C.pdf)

<br>

## 💻 기술 스택
### Backend
- **언어** : Java 17  
- **프레임워크** : Spring Boot 3.3.5
- **DB 및 데이터 관리** : MySQL, Spring Data JPA, Spring Data JDBC
- **보안** : Spring Security 6, JWT
- **API** : REST API (API 문서화)
- **클라우드 및 스토리지** : AWS S3, AWS SDK
- **기타 라이브러리** : Lombok, JSON

### Frontend
- **언어** : JavaScript (ES6), HTML5, CSS3

### Infra
- **서버 및 배포** : AWS EKS, Docker, AWS CloudFormation
- **컨테이너 레지스트리** : AWS ECR (Elastic Container Registry)
- **데이터베이스** : MySQL 8.0.36 (Amazon RDS)
- **CI/CD** : GitHub Actions, ArgoCD
- **모니터링** : Prometheus, Grafana

<br>

## ⚙️ 주요 기능 (Features)
- **서비스**
  - 전체 게시물 조회
  - 카테고리별 게시물 조회
  - 최고의 자랑 Top 5 조회
  - 게시물 열람
  - 좋아요 표현
- **회원**
  - 회원 가입
  - 로그인 (jwt 인증)
  - 로그아웃
  - 게시물 CRUD : 사진 첨부 
  - 댓글 CRUD
- **관리자**
  - 카테고리 CRUD

<br>

## ☁️ 배포 (Deployment)
- **CI/CD** : GitHub Actions → Docker → ECR → ArgoCD → EKS 배포
- **모니터링** : Prometheus + Grafana

<br>

## 💻 개발 환경 (Development Environment)
- **Java 17**
- **Spring Boot 3.3.5**
- **Maven**
- **MySQL 8.0.36**
- **Spring Security 6**
- **JWT 인증**

<br>

## 👥 조원
- 조장 : **이홍비** ( redrain@yu.ac.kr )
- 조원 : **김예린** ( sky3nlq@gmail.com )
- 조원 : **배희창** ( madsenss@naver.com )
- 조원 : **전익주** ( zza1541@naver.com )
- 조원 : **채혜송** ( chg5264@naver.com )

<br>

## 📄 application.properties

```
spring.application.name=showu

server.port=8081

# AWS RDS MySQL
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://[]:3306/showu?serverTimezone=Asia/Seoul
spring.datasource.username=root
spring.datasource.password=rootroot

# Local MySQL
#spring.datasource.url=jdbc:mysql://localhost:3306/showu?serverTimezone=Asia/Seoul
#spring.datasource.username=lion4
#spring.datasource.password=lion4

# JPA
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.show-sql=true

# Thymeleaf
spring.thymeleaf.encoding=UTF-8
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.cache=false
spring.thymeleaf.check-template-location=true

# Multipart
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB

# AWS S3
# Accesss Key
cloud.aws.credentials.access-key=
# Secret Key
cloud.aws.credentials.secret-key=
# Bucket Name
cloud.aws.s3.bucket=
# Region
cloud.aws.region.static=
cloud.aws.stack.auto-=false

```
