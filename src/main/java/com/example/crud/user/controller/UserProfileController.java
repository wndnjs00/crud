//package com.example.crud.user.controller;
//
//import com.example.crud.mapper.UserProfileMapper;
//import com.example.crud.user.model.UserProfile;
//import com.example.crud.auth.service.UserService;
//import com.lodong.utilsmodule.dto.ApiResponse;
//import com.lodong.utilsmodule.enums.SuccessCode;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//import java.util.UUID;
//

import com.example.crud.auth.dto.UserResponseDto;
import com.example.crud.auth.model.User;

import java.util.NoSuchElementException;

//// 컨트롤러 -> 사용자의 API를 처리해줌
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/user")
//public class UserProfileController {
//    // UserProfileMapper를 사용해서 실제 테이블(데이터베이스)에서 사용자정보를 호출,생성,수정,삭제하도록 함!
//    private UserProfileMapper mapper;
//    private final UserService userService;
//
//    // id를 인자로 받아서 해당 UserProfile의 정보를 json형태로 전달하는 API를 만들것임
//    @GetMapping("/{id}")
//    public ApiResponse<?> getUserProfile(@PathVariable("id") UUID uuid){
//        Object object = userService.getUserProfile(uuid);
//        return ApiResponse.success(SuccessCode.SELECT_SUCCESS, object);
//    }

//
//    // 리스트 전체를 호출하는 API 만들기
//    @GetMapping("/all")
//    public List<UserProfile> getUserProfileList(){
//        return mapper.getUserProfileList();
//    }
//
//    // 새로운 데이터를 생성하려면 post방식! (id는 테이블생성시 자동생성되도록 만들었음)
//    @PostMapping("/user")
//    public void postUserProfile(@RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address){
//        mapper.insertUserProfile(name,phone,address);
//    }
//
//
//    // 수정하려면 put방식!
//    @PutMapping("/{id}")
//    public void putUserProfile(@PathVariable("id") int id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address){
//        mapper.updateUserProfile(id,name,phone,address);
//    }
//
//    // 삭제하려면 delete방식!
//    @DeleteMapping("/{id}")
//    public void deleteUserProfile(@PathVariable("id") int id){
//        mapper.deleteUserProfile(id);
//    }
//}
