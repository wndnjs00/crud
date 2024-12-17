package com.example.crud.controller;

import com.example.crud.model.UserProfile;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
        return userMap.get(id);  //미리 정의한 userMap에서 요청한 id에 해당하는 UserProfile의 정보를 가져와서 리턴해주면됨!!
    }
}
