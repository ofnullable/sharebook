# SHAREBOOK/server

`Java 11.0.1 - Spring Boot, Spring Security, Spring data jpa`

## 실행방법

프로젝트를 받지 않았다면 우선 프로젝트를 로컬에 clone 받는다.

```
// terminal
> cd path/to/download
> git clone https://github.com/ofnullable/sharebook.git
```

이 후 server 폴더로 이동 후 gradlew을 이용하여 실행한다. `// port: 8081`

```
// terminal
> cd sharebook/server
> gradlew bootRun -Dfile.encoding=UTF-8 // 한글깨짐 방지
```

## 프로젝트 구조

server는 file-service 모듈과 api 모듈로 구분되어있다.

- module-api
  - Application의 시작점이며 대부분의 코드가 이 모듈에 작성되어있다.
  - Database, File upload 관련한 설정은 이 모듈의 [application.yml](module-api/src/main/resources/application.yml)에 작성된다.
- module-file-service
  - File upload 방법을 설정파일로 결정하기 위해 custom starter로 작성하였다.
  - `sharebook.file.ftp`의 각 내용이 설정되면 FTP Server에 파일을 업로드 한다.
  - 설정되지 않으면 build directory의 `resources/static`폴더에 업로드 한다.

## 파일 업로드 방법 결정

파일 업로드는 두가지 방법 중 원하는 방법을 선택할 수 있다.

- **Spring boot의 Resources directory에 직접 업로드 (default)**
  - 기본 업로드폴더: `/server/module-api/build/resources/static/image/${YYYYMMDD}`
  - 별 다른 설정을 하지 않으면 기본적으로 이 방법으로 파일을 업로드한다.
  - **Resources안에 직접 업로드 되기 때문에 별 다른 라우팅이 필요하지 않다.**
  - 업로드폴더는 [application.yml](module-api/src/main/resources/application.yml)에 `sharebook.file.basePath`옵션으로 변경할 수 있다.
  - `basePath`를 변경하면 `/resources/static/${basePath}/${YYYYMMDD}`로 변경된다.
  - 이 방법은 `gradle clean` 수행 시 업로드 된 파일이 삭제되므로 주의가 필요하다.

- **FTP Server를 활용한 File 전송 및 저장**
  - 기본 업로드폴더: `sharebook/image/${YYYYMMDD}`
  - [application.yml](module-api/src/main/resources/application.yml)의 설정으로 FTP Server에 파일을 전송할 수 있다.
  - 주석처리된 예시를 해제하고 FTP Server 접속 정보를 입력해서 사용할 수 있다.
  - 마찬가지로 `basePath` 설정을 통해 `${basePath}/${YYYYMMDD}`로 업로드 경로를 수정할 수 있다.
  - 이미지들을 application과 분리하여 관리하는 장점등이 있지만 FTP Server가 필요하다.
