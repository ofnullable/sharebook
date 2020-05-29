# SHAREBOOK

`학원 또는 직장등의 조직 내 조직원들의 자유로운 도서 공유를 위한 Web App`

[BITCAMP](https://bitcamp.co.kr/index.php?main_page=home)에서 진행했던 프로젝트의 개선버전  
**[origin-repository](https://github.com/thdnthdn2/publicshare)**, **[forked-repository](https://github.com/jooonak/publicshare)**

## Key Concepts

- 조직원들의 도서 공유를 장려하기 위해 조직에서 운영하는 설치형 서비스
- 회원가입 이후 도서등록 및 대여기능을 사용할 수 있다.
- 다른 사용자의 도서에 평점과 함께 리뷰를 작성할 수 있다.
- 자신이 등록한 도서를 상태별로 조회할 수 있다.
- 자신이 대여한 도서를 상태별로 조회할 수 있다.

## 개발환경

- Windows10
- VSCode (front) + Intellij (back)
- Node.js: 10.15.3
- Java: 11.0.1
- MySQL: 8.0.13

## 실행방법

프로젝트를 받지 않았다면 우선 프로젝트를 로컬에 clone 받는다.

```
// terminal
> cd path/to/download
> git clone https://github.com/ofnullable/sharebook.git
```

- front: [실행방법](./front/README.md#실행방법)
- server: [실행방법](./server/README.md#실행방법)

## 변경사항

- java8 -> java11
- maven -> gradle
- spring 4.3 -> spring-boot 2.2.0
- jsp -> ~~thymeleaf~~ -> HTTP API + React (Next) App
- commons-dbcp -> HikariCP
- MyBatis -> JPA
- apache commons-fileupload -> spring boot default + spring-integration-ftp
- login with interceptor -> spring security
