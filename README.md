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
      <img src="./docs/주요_기능/포토스팟_콜렉션/1.gif" width=70%>
      <li>이미 존재하는 이메일일 경우</li>
      <img src="./docs/주요_기능/포토스팟_콜렉션/1.gif" width=70%>
      <li>필수값 누락하여 요청할 경우</li>
      <img src="./docs/주요_기능/포토스팟_콜렉션/1.gif" width=70%>
    </ul>
  </div>
</details>

<details>
  <summary><b>로그인</b></summary>
  <div markdown="1">
    <ul>
      <li>로그인 성공</li>
      <img src="./docs/주요_기능/포토스팟_콜렉션/1.gif" width=70%>
      <li>존재하지 않은 이메일일 경우</li>
      <img src="./docs/주요_기능/포토스팟_콜렉션/1.gif" width=70%>
      <li>비밀번호가 잘못된 경우</li>
      <img src="./docs/주요_기능/포토스팟_콜렉션/1.gif" width=70%>
      <li>필수값 누락하여 요청할 경우</li>
      <img src="./docs/주요_기능/포토스팟_콜렉션/1.gif" width=70%>
      <li>올바른 accessToken으로 요청</li>
      <img src="./docs/주요_기능/포토스팟_콜렉션/1.gif" width=70%>
       <li>올바른 refreshToken으로 요청</li>
      <img src="./docs/주요_기능/포토스팟_콜렉션/1.gif" width=70%>
      <li>만료된 refreshToken으로 요청</li>
      <img src="./docs/주요_기능/포토스팟_콜렉션/1.gif" width=70%>
    </ul>
  </div>
</details>

<details>
  <summary><b>로그아웃</b></summary>
  <div markdown="1">
    <ul>
      <li>로그아웃 성공</li>
      <img src="./docs/주요_기능/포토스팟_콜렉션/1.gif" width=70%>
    </ul>
  </div>
</details>

<details>
  <summary><b>회원탈퇴</b></summary>
  <div markdown="1">
    <ul>
      <li>회원탈퇴 성공</li>
      <img src="./docs/주요_기능/포토스팟_콜렉션/1.gif" width=70%>
      <li>탈퇴한 사용자정보로 로그인 할경우</li>
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
      <li>설멸</li>
      <li>설명</li>
      <img src="./docs/주요_기능/포토스팟_콜렉션/1.gif" width=70%>
    </ul>
  </div>
</details>

<details>
  <summary><b>특정 연락처 데이터 조회</b></summary>
  <div markdown="1">
    <ul>
      <li>uuid로 해당 사용자 조회</li>
      <img src="./docs/주요_기능/포토스팟_콜렉션/1.gif" width=70%>
      <li>존재하지 않는 uuid로 조회한 경우</li>
      <img src="./docs/주요_기능/포토스팟_콜렉션/1.gif" width=70%>
    </ul>
  </div>
</details>

<details>
  <summary><b>연락처 데이터 추가</b></summary>
  <div markdown="1">
    <ul>
      <li>설명</li>
      <li>설명</li>
      <img src="./docs/주요_기능/포토스팟_콜렉션/1.gif" width=70%>
    </ul>
  </div>
</details>


<details>
  <summary><b>연락처 데이터 수정</b></summary>
  <div markdown="1">
    <ul>
      <li>설명</li>
      <li>설명</li>
      <img src="./docs/주요_기능/포토스팟_콜렉션/1.gif" width=70%>
    </ul>
  </div>
</details>


<details>
  <summary><b>연락처 데이터 삭제</b></summary>
  <div markdown="1">
    <ul>
      <li>설명</li>
      <li>설명</li>
      <img src="./docs/주요_기능/포토스팟_콜렉션/1.gif" width=70%>
    </ul>
  </div>
</details>
