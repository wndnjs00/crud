package com.example.crud.controller;

import com.example.crud.mapper.UserProfileMapper;
import com.example.crud.model.UserProfile;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// 컨트롤러 -> 사용자의 API를 처리해줌
@RestController
public class UserProfileController {
    // UserProfileMapper를 사용해서 실제 테이블(데이터베이스)에서 사용자정보를 호출,생성,수정,삭제하도록 함!
    private UserProfileMapper mapper;

    // UserProfileController 생성자의 파라미터를 mapper로 설정해주고, 전달받은 mapper를 내부에 저장하는 생성자를 만들면, 스프링부트가 알아서 처리해줌 -> 그러면 우리는 전달된 mapper를 이용해서 API를 호출할수있음
    public UserProfileController(UserProfileMapper mapper){
        this.mapper = mapper;
    }

    // id를 인자로 받아서 해당 UserProfile의 정보를 json형태로 전달하는 API를 만들것임
    @GetMapping("/user/{id}")
    public UserProfile getUserProfile(@PathVariable("id") String id){
        return mapper.getUserProfile(id);   // mapper에 정의된 getUserProfile이라는 API를 호출!  // getUserProfile가 실행이되면 얘와 연결된 SQL문이(@Select("SELECT * FROM UserProfile WHERE id = ${id}") 실행이되서 해당되는 id를 갖는 UserProfile의 정보가 객체로 반환이됨 -> 그러면 이걸 JSON형태로 다시 전달하게되는 형태!!!
    }

    // 리스트 전체를 호출하는 API 만들기
    @GetMapping("/user/all")
    public List<UserProfile> getUserProfileList(){
        return mapper.getUserProfileList();
    }

    // 새로운 데이터를 생성하려면 post방식!
    // 수정할때도 동일하게 id를 사용해서 수정하도록 만들었음 / id같은것만 putMapping으로 전달하고, 나머지는 @RequestParam을 사용해서 HTTP프로토콜의 파라미터형태로 전달하는것이 일반적임! -> 추가할 id,name,phone,address를 파라미터로 전달받음
    @PostMapping("/user/{id}")
    public void postUserProfile(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address){
        mapper.insertUserProfile(id,name,phone,address);
    }

    // 수정하려면 put방식!
    @PutMapping("/user/{id}")
    public void putUserProfile(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address){
        mapper.updateUserProfile(id,name,phone,address);
    }

    // 삭제하려면 delete방식!
    @DeleteMapping("/user/{id}")
    public void deleteUserProfile(@PathVariable("id") String id){
        mapper.deleteUserProfile(id);
    }
}
