# Lost and Found Platform

유실물 찾기 플랫폼은 사용자가 잃어버린 물건을 등록하고 찾을 수 있도록 돕는 웹 서비스

## Table of Contents

- [프로젝트 소개](#프로젝트-소개)
- [기술 스택](#기술-스택)
- [설치 및 실행](#설치-및-실행)
- [기능 소개](#기능-소개)
- [스크린샷](#스크린샷)
- [문제 해결 및 개선 사항](#문제-해결-및-개선-사항)
- [기여 방법](#기여-방법)
- [라이선스](#라이선스)
- [작성자](#작성자)

## 프로젝트 소개

유실물 찾기 플랫폼은 사람들이 잃어버린 물건을 쉽게 등록하고 찾을 수 있도록 돕는 웹 서비스입니다. 이 프로젝트의 주요 목표는 유실물을 효율적으로 관리하고 찾을 수 있는 환경을 제공하는 것입니다.

### 주요 기능

- **유실물 등록:** 사용자가 잃어버린 물건을 등록할 수 있는 기능.
- **유실물 검색 및 필터링:** 사용자가 잃어버린 물건을 키워드나 카테고리로 검색하고 필터링할 수 있는 기능.
- **유실물 상세 정보 확인:** 등록된 유실물의 상세 정보를 확인하는 기능.
- **유실물 상태 업데이트:** 유실물의 상태를 '발견됨', '반환됨' 등으로 업데이트할 수 있는 기능.

## 기술 스택

- **Backend:** Spring Boot - 강력하고 효율적인 Java 기반 웹 프레임워크
- **Database:** MySQL - 데이터 저장을 위한 관계형 데이터베이스
- **Persistence Layer:** JDBC - Java 기반 데이터베이스 연결 기술
- **Build Tool:** Maven - 프로젝트 빌드 및 의존성 관리를 위한 도구
- **Frontend:** Thymeleaf - 서버 사이드 템플릿 엔진, HTML, CSS, JavaScript

## 설치 및 실행

### Prerequisites

프로젝트를 실행하기 전에 필요한 사전 준비 사항:
- Java 8 이상 설치
- Maven 설치
- MySQL 설치 및 실행

### 설치

1. 레포지토리를 클론합니다.

    ```bash
    git clone https://github.com/username/lost-and-found-platform.git
    cd lost-and-found-platform
    ```

2. MySQL에서 데이터베이스를 생성합니다.

    ```sql
    CREATE DATABASE lost_and_found_db;
    ```

3. `src/main/resources/application.properties` 파일을 열어 데이터베이스 설정을 수정합니다.

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/lost_and_found_db
    spring.datasource.username=yourUsername
    spring.datasource.password=yourPassword
    ```

4. Maven을 사용하여 프로젝트를 빌드합니다.

    ```bash
    mvn clean install
    ```

5. 애플리케이션을 실행합니다.

    ```bash
    mvn spring-boot:run
    ```

6. 웹 브라우저에서 `http://localhost:8080`에 접속합니다.

## 기능 소개

### 유실물 등록

사용자가 잃어버린 물건의 제목, 설명, 카테고리, 이미지 등을 입력하여 등록할 수 있는 기능입니다.

### 유실물 검색 및 필터링

사용자가 잃어버린 물건을 키워드나 카테고리로 검색하고, 등록 날짜나 위치 등으로 필터링할 수 있는 기능입니다.

### 유실물 상세 정보 확인

등록된 유실물의 상세 정보를 확인할 수 있으며, 물건의 상태를 '발견됨', '반환됨' 등으로 업데이트할 수 있습니다.

### 유실물 상태 업데이트

유실물의 상태를 업데이트하여, 현재 상태를 '발견됨', '반환됨' 등으로 변경할 수 있습니다.

## 스크린샷

프로젝트의 주요 화면을 시각적으로 보여줍니다.

### 유실물 등록

사용자가 잃어버린 물건을 등록할 수 있는 페이지입니다.

![유실물 등록](https://example.com/lost_item_registration.png)

### 유실물 검색

사용자가 잃어버린 물건을 검색할 수 있는 페이지입니다.

![유실물 검색](https://example.com/lost_item_search.png)

### 유실물 상세 정보

유실물의 상세 정보를 보여주는 페이지입니다.

![유실물 상세 정보](https://example.com/lost_item_detail.png)

## 문제 해결 및 개선 사항

### 문제 해결

개발 과정에서 마주한 문제와 이를 해결한 방법을 설명합니다.

- **데이터베이스 연결 문제:** MySQL JDBC 드라이버 설정을 통해 해결. 데이터베이스 연결 설정을 수정하고, 필요한 드라이버를 프로젝트에 포함시켰습니다.
- **성능 최적화:** 쿼리 최적화 및 인덱스 추가를 통해 성능 문제를 해결. 데이터베이스 쿼리를 최적화하고 필요한 인덱스를 추가하여 검색 속도를 개선했습니다.

### 향후 개선 사항

프로젝트를 개선할 수 있는 부분을 설명합니다.

- **모바일 UI 최적화:** 모바일 환경에서 더 나은 사용자 경험을 제공하기 위해 UI를 최적화할 예정입니다.
- **사용자 알림 기능 추가:** 유실물 등록 및 상태 변경 시 사용자에게 알림을 보내는 기능을 추가할 예정입니다.
- **관리자 페이지 추가:** 유실물 관리 기능을 강화하기 위해 관리자 페이지를 추가할 예정입니다.

## 기여 방법

이 프로젝트에 기여하는 방법을 안내합니다.

1. 이 레포지토리를 포크합니다.
2. 새로운 브랜치를 생성합니다. (`git checkout -b feature/YourFeature`)
3. 변경 사항을 커밋합니다. (`git commit -m 'Add some feature'`)
4. 브랜치에 푸시합니다. (`git push origin feature/YourFeature`)
5. 풀 리퀘스트를 생성합니다.

## 라이선스

이 프로젝트는 MIT 라이선스에 따라 라이선스가 부여됩니다. 자세한 내용은 [LICENSE](LICENSE) 파일을 참조하세요.

## 작성자

프로젝트 작성자에 대한 정보를 제공합니다.

- **이름:** 홍길동
- **이메일:** honggildong@example.com
- **GitHub:** [github.com/username](https://github.com/username)
