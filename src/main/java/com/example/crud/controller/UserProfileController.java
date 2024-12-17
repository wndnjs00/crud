package com.example.crud.controller;

import com.example.crud.model.UserProfile;
import jakarta.annotation.PostConstruct;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 컨트롤러 -> 사용자의 API를 처리해줌
@RestController
public class UserProfileController {
    // 우선 데이터베이스에 사용자 정보를 저장하는것은 나중에 하기로하고,

    // UserProfile을 담는 Map을 선언
    private Map<String, UserProfile> userMap;

    // UserProfileController을 만들고 직후에 바로 호출해주기때문에 -> @PostConstruct사용해줌
    // 임시로 임의의 데이터 집어넣음(3명의 데이터를 Map으로 가지고있는 데이터를 만들어줌)
    @PostConstruct
    public void init(){
        userMap = new HashMap<String, UserProfile>();
        userMap.put("1", new UserProfile("1", "임현식", "1111-1111","서울시 강남구 대치1동"));
        userMap.put("2", new UserProfile("2", "서은광", "1111-1112","서울시 강남구 대치2동"));
        userMap.put("3", new UserProfile("3", "육성재", "1111-1113","서울시 강남구 대치3동"));
    }

    // id를 인자로 받아서 해당 UserProfile의 정보를 json형태로 전달하는 API를 만들것임
    // @PathVariable("id")를 지정해서 /user/{id}에 해당하는 id를 id로 인식하고, 그 id를 파라미터로 입력해서 API를 호출하도록 만드는것임
    // 즉, http://localhost:8080/user/1 => 입력시, API가 호출되도록 만듬
    @GetMapping("/user/{id}")
    public UserProfile getUserProfile(@PathVariable("id") String id){
        return userMap.get(id);  // 미리 정의한 userMap에서, 요청한 id에 해당하는 UserProfile의 정보를 가져와서 리턴해주면됨!! (그냥 UserProfile이라는 객체를 리턴하면 이 객체를 자동으로 JSON형태로 맵핑해서 클라이언트에게 전달해주게됨)
    }

    // 리스트 전체를 호출하는 API 만들기
    @GetMapping("/user/all")
    public List<UserProfile> getUserProfileList(){
        return new ArrayList<UserProfile>(userMap.values());    // userMap의 3개의 value를 ArrayList로 변환해서 그것을 리턴하게됨
    }

    // 새로운 데이터를 생성하려면 post방식!
    // 수정할때도 동일하게 id를 사용해서 수정하도록 만들었음 / id같은것만 putMapping으로 전달하고, 나머지는 @RequestParam을 사용해서 HTTP프로토콜의 파라미터형태로 전달하는것이 일반적임! -> 추가할 id,name,phone,address를 파라미터로 전달받음
    @PostMapping("/user/{id}")
    public void postUserProfile(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address){
        UserProfile userProfile = new UserProfile(id, name, phone, address);
        userMap.put(id, userProfile);   // userMap에 id를 key로하는 userProfile객체를 추가!
    }

    // 수정하려면 put방식!
    @PutMapping("/user/{id}")
    public void putUserProfile(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address){
        UserProfile userProfile = userMap.get(id);  // 기존에 가지고있는 정보에서 사용자 id객체를 찾아서
        userProfile.setName(name);      // name도 바꾸고(수정하고)
        userProfile.setPhone(phone);    // phone도 바꾸고(수정하고)
        userProfile.setAddress(address); // address도 바꿈(수정하고)
    }
}
