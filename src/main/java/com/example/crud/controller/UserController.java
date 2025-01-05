package com.example.crud.controller;

import com.example.crud.mapper.UserMapper;
import com.example.crud.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    // UserMapper를 사용해서 실제 테이블(데이터베이스)에서 회원가입 정보를 가져올수있도록함
    private UserMapper mapper;

    // UserController 생성자의 파라미터를 mapper로 설정해주고, 전달받은 mapper를 내부에 저장하는 생성자를 만들면, 스프링부트가 알아서 처리해줌 -> 그러면 우리는 전달된 mapper를 이용해서 API를 호출할수있음
    public UserController(UserMapper mapper){
        this.mapper = mapper;
    }

    // 회원가입 API
    @PostMapping("/user/register")
    public ResponseEntity<String> registerUser(@RequestParam("email") String email,
                                               @RequestParam("password") String password,
                                               @RequestParam("name") String name,
                                               @RequestParam("phone") String phone,
                                               @RequestParam("address") String address){
        try {
            mapper.insertUser(email, password, name, phone, address);
            return ResponseEntity.ok("회원가입 성공");    // postman으로 API테스트시 성공하면 뜨도록 메시지설정
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입 실패" + e.getMessage());
        }
    }

    // 로그인 API
    @PostMapping("/user/login")
    public ResponseEntity<User> longinUser(@RequestParam("email") String email, @RequestParam("password") String password){

        User user = mapper.loginUser(email, password);

        if (user != null){
            return ResponseEntity.ok(user);  // 성공 시 User 객체 반환
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();  // 인증 실패시
        }
    }
}
