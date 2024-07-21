# Lost and Found Platform

![readme_mockup2](https://github.com/khr316/sprint1/blob/main/%EB%A9%94%EC%9D%B8.png)

## 팀원 구성

<div align="center">

| **박소라** | **유영훈** | **노광우** | **김혜림** |
| :------: |  :------: | :------: | :------: |
| P | Y | N | K |
| [@yuuminaegger](https://github.com/yuuminaegger) | [@TyulRip](https://github.com/TyulRip) | [@ngw960](https://github.com/ngw960) | [@khr316](https://github.com/khr316) |

</div>

## 역할 분담

### 박소라 P

- 백엔드 Controller, Dao, Html
- 1. 회원가입
     아이디 중복체크, 빈 칸 입력 필수, 비밀번호 & 비밀번호 확인 일치
  2. 로그인
     DB 에 저장 된 회원만 로그인 가능
     잘못된 아이디 or 비밀번호 입력 시 에러 메세지 출력
  3. 관리자 페이지
     회원 탈퇴, 게시글 & 댓글 삭제
  4. 게시글
     로그인 = 글 작성자 : 게시글 수정 & 삭제
     로그인 = 댓글 작성자 : 댓글 수정 & 삭제
     로그인 = 관리자 :  게시글, 댓글 삭제
     
- 프론트엔드 css
- 1. 메인페이지
  2. 로그인
  3. 회원가입

<br>
    
### 유영훈 Y

- 프론트엔드 Html수정 + css
- 1. 분실물 게시판
  2. 습득물 게시판
  3. 분실물 검색 결과
  4. 습득물 검색 결과
  5. 쪽지 확인
  6. 쪽지 보내기
  7. 쪽지 목록
  8. 로그인
  9. 회원가입
  10. 마이페이지
  11. 관리자 페이지
  12. 글쓰기
  13. 게시글

<br>

### 노광우 N

- 조장
- 백엔드 Controller, Dao, Html
- 1. 메인페이지
  2. 분실물 게시판
  3. 습득물 게시판
  4. 분실물 검색 결과
  5. 습득물 검색 결과
     최신순, 오래된 순
  6. 비밀번호 찾기
     가입한 이메일로 임시 비밀번호 발금
     => K 가 구현한 비밀번호 찾기 기능 개선

- 총괄 관리 및 서류 작성

<br>

### 김혜림 K

- 백엔드 Controller, Dao, Html
- 1. 마이페이지
     회원 정보 수정 (비밀번호, 전화번호, 이메일)
     쪽지 확인
     작성한 게시글
  2. 글쓰기
     위치 검색, 물품 선택, 이미지 등록
  3. 쪽지
     받은 & 보낸 쪽지 확인, 답장기능, 새로운 쪽지 알림
  4. 비밀번호 찾기
     아이디 입력 시 비밀번호 발급
     => 개인정보 보호 X, 다른 사람도 아이디를 안다면 비번 확인 가능

- 프론트엔드 css
- 1. 메인페이지
  2. 로그인
  3. 회원가입
    
<br>

<br>

## 프로젝트 소개

#### LUCKY PYNK
- 물건을 분실한 경우 글을 등록하여 분실물을 찾을 수 있는 플랫폼
- 물건을 습득한 경우 글을 등록하여 물건의 주인을 찾을 수 있는 플랫폼
- 회원가입 필수 (회원만 이용 가능)
- 키워드, 지역 별로 게시글 검색 가능
- 게시글에 댓글 기능 추가
- 쪽지 기능을 넣어 회원들간의 소통을 중시

#### 목표
- 효율적인 분실물 관리 서비스 플랫폼 구축
- 사용친화적이고 직관적인 UI / UX 디자인 구현

#### 목적
- 분실물 찾기 성공률 향상
- 커뮤니티 형성 및 신뢰 구축
- 시간 및 비용 절감
- 사회적 문제 해결 기여

<br>

### 요구사항 분석

1. 분실물 등록
   사용자가 분실물 정보를 입력하여 게시글 등록
2. 분실물 검색
   키워드 및 지역 별로 검색 가능
3. 사용자 인증 및 관리
   회원가입, 로그인, 로그아웃
4. 분실물 상태 관리
   분실물의 상태 업데이트 가능 (찾는 중 or 찾기 완료)

<br>

### 개발 환경
- FE : HTML CSS JavaScript Thymeleaf
- BE : Java JDBC SpringBoot Maven Spring-Data-JPA Lombok
- DataBase : MySQL
- 협업 툴 : Github, FileZilla


<div>
<img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> 
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
</div>

<br>

### 데이터베이스 설계

![데이터베이스설계](https://github.com/user-attachments/assets/257b8ebf-e112-479a-9770-9fb3d79e0e12)

<br>

### 프로젝트 구조

```
src
├── main
│   ├── java
│   │   └── com
│   │       └── project
│   │           └── sprint1
│   │               ├── controller
│   │               │   ├── AdminController.java
│   │               │   ├── BoardController.java
│   │               │   ├── MainController.java
│   │               │   ├── MessageController.java
│   │               │   ├── MypageController.java
│   │               │   ├── PasswordController.java
│   │               │   └── UserController.java
│   │               ├── dao
│   │               │   ├── AdminDao.java
│   │               │   ├── BoardDao.java
│   │               │   ├── MainDao.java
│   │               │   ├── MessageDao.java
│   │               │   ├── MypageDao.java
│   │               │   ├── PasswordDao.java
│   │               │   ├── PostDao.java
│   │               │   └── UserDao.java
│   │               ├── service
│   │               │   └── EmailService.java
│   │               └── Sprint1Application.java
│   └── resources
│       ├── static
│       │   ├── css
│       │   └── img
│       ├── js
│       └── templates
│           ├── board
│           │   ├── category.html
│           │   ├── detail.html
│           │   ├── insert.html
│           │   └── location.html
│           ├── menu
│           │   ├── finditem.html
│           │   ├── lostitem.html
│           │   ├── searchfi.html
│           │   └── searchli.html
│           ├── message
│           │   └── message_insert.html
│           │   └── message_notice.html
│           │   └── message.html
│           ├── adminpage.html
│           ├── find-password.html
│           ├── login.html
│           ├── main.html
│           ├── mypage.html
│           ├── password.html
│           └── signup.html
│       └── application.properties

```
<br>

### 시스템 구조

![시스템구조](https://github.com/user-attachments/assets/cb375675-957d-4461-9265-e00fed28328d)

<br>
