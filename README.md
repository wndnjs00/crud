# 📞 연락처 서버
Spring Boot를 활용한 연락처 REST API 서버
<br/>
## 💻 개발 환경
- Version : Java 17
- IDE : IntelliJ
- Framework : SpringBoot
- ORM : JPA
- DataBase: MySQl

<br/><br/>
## 📌 주요기능
**로그인 & 회원가입**
<details>
  <summary><b>회원가입</b></summary>
  <div markdown="1">
    <ul>
      <li>회원가입 성공</li>
      POST -> http://localhost:8080/auth/register
      <img src="https://github.com/user-attachments/assets/1cb2cce2-053b-4974-8841-a1c127957d2e" width=70%>
      <br/><br/>
      <li>이미 존재하는 이메일일 경우</li>
      POST -> http://localhost:8080/auth/register
      <img src="https://github.com/user-attachments/assets/f25d9ad7-b644-4f1b-8028-68710eee6379" width=70%>
      <br/><br/>
      <li>필수값 누락하여 요청할 경우 (email값 누락하여 요청)</li>
      POST -> http://localhost:8080/auth/register
      <img src="https://github.com/user-attachments/assets/ac50083d-4e93-4c49-b66f-f7bc035e4476" width=70%>
    </ul>
  </div>
</details>

<details>
  <summary><b>로그인</b></summary>
  <div markdown="1">
    <ul>
      <li>로그인 성공</li>
      POST -> http://localhost:8080/auth/login
      <img src="https://github.com/user-attachments/assets/8446b777-70b1-4840-bb56-f03c915bd78f" width=70%>
      <br/><br/>
      <li>존재하지 않은 이메일일 경우</li>
      POST -> http://localhost:8080/auth/login
      <img src="https://github.com/user-attachments/assets/229dc071-27a8-41cd-8091-a6d643faa914" width=70%>
      <br/><br/>
      <li>비밀번호가 잘못된 경우</li>
      POST -> http://localhost:8080/auth/login
      <img src="https://github.com/user-attachments/assets/7ee1948e-caec-4e88-9a80-ccf4edc90338" width=70%>
      <br/><br/>
      <li>필수값 누락하여 요청할 경우(email 누락하여 요청)</li>
      POST -> http://localhost:8080/auth/login
      <img src="https://github.com/user-attachments/assets/8e491cac-8e7c-4bda-8637-a8af14077cb4" width=70%>
      <br/><br/>
      <li>올바른 accessToken으로 요청</li>
      GET -> http://localhost:8080/auth/info
      <br/><br/> 로그인에서 발급받은 accessToken을 통해 회원정보 조회
      <img src="https://github.com/user-attachments/assets/e04a0b9b-8dd0-45f2-a214-2a94ba2adc2c" width=70%>
      <br/><br/>
       <li>올바른 refreshToken으로 요청</li>
      POST -> http://localhost:8080/auth/refresh
      <br/><br/> accessToken 만료시, refreshToken으로 재요청
      <img src="https://github.com/user-attachments/assets/50def57e-d260-4670-8f91-abaf6551ac22" width=70%>
      <br/><br/>
      <li>만료된 refreshToken으로 요청</li>
      POST -> http://localhost:8080/auth/refresh
      <img src="https://github.com/user-attachments/assets/b7068ff1-9fb4-4103-8720-a3a1ee8f7c84" width=70%>
      <br/><br/>
    </ul>
  </div>
</details>

<details>
  <summary><b>로그아웃</b></summary>
  <div markdown="1">
    <ul>
      <li>로그아웃 성공</li>
      POST -> http://localhost:8080/auth/logout
      <br/><br/> 로그아웃시, DB에 있는 RefreshToken값 삭제
      <img src="https://github.com/user-attachments/assets/f2a542d7-067b-4634-94c8-14993fa07f0c" width=70%>
      <br/><br/>
    </ul>
  </div>
</details>
      
<details>
  <summary><b>회원탈퇴</b></summary>
  <div markdown="1">
    <ul>
      <li>회원탈퇴 성공</li>
      POST -> ????
      <img src="./docs/주요_기능/포토스팟_콜렉션/1.gif" width=70%>
      <br/><br/>
      <li>탈퇴한 사용자정보로 로그인 할경우</li>
      POST -> ????
      <img src="./docs/주요_기능/포토스팟_콜렉션/1.gif" width=70%>
    </ul>
  </div>
</details>


<br/><br/>
**User**
<details>
  <summary><b>전체 연락처 데이터 조회</b></summary>
  <div markdown="1">
    <ul>
      <li>전체 데이터 조회</li>
      <li>GET -> http://localhost:8080/user/all<li>
      <img src= "https://github.com/user-attachments/assets/b6cb1966-66d1-4fb8-b745-4d48f8f48c8e" width=70%>
    </ul>
  </div>
</details>
      

<details>
  <summary><b>특정 연락처 데이터 조회</b></summary>
  <div markdown="1">
    <ul>
      <li>uuid로 특정 사용자 조회</li></li>
      GET -> http://localhost:8080/user/uuid
      <img src= "https://github.com/user-attachments/assets/b4cf5405-790e-44d1-a997-3b0e8fe7713a" width=70%>
      <br/><br/>
      <ul>
      <li>존재하지 않는 uuid로 조회한 경우</li>
      GET -> http://localhost:8080/user/uuid
      <img src="https://github.com/user-attachments/assets/ea54c46c-cc3b-4f9d-a6d6-9f3a487f5c4b" width=70%>
      <br/><br/>
    </ul>
  </div>
</details>


<details>
  <summary><b>연락처 데이터 추가</b></summary>
  <div markdown="1">
    <ul>
      <li>새로운 연락처데이터 추가</li>
      POST -> http://localhost:8080/user/new
      <img src="https://github.com/user-attachments/assets/87567393-705f-40fb-b01d-751bbb5be61e" width=70%>
      <br/><br/>
    </ul>
  </div>
</details>


<details>
  <summary><b>연락처 데이터 수정</b></summary>
  <div markdown="1">
    <ul>
      <li>uuid로 기존 사용자 수정</li>
      PUT -> http://localhost:8080/user/uuid
      <br/><br/> 수정할 사용자의 uuid를 입력해서 사용자정보 수정
      <img src="https://github.com/user-attachments/assets/f9b1a67a-1c27-4bbb-addf-a029338fc286" width=70%>
      <br/><br/>
    </ul>
  </div>
</details>


<details>
  <summary><b>연락처 데이터 삭제</b></summary>
  <div markdown="1">
    <ul>
      <li>uuid로 기존 사용자 삭제</li>
      DELETE -> http://localhost:8080/user/uuid
      <img src="https://github.com/user-attachments/assets/61ef4934-a0a7-478f-b5f8-2705b2de48cb" width=70%>
      <br/><br/>
    </ul>
  </div>
</details>
