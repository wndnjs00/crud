package com.example.crud.mapper;

import com.example.crud.model.UserProfile;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserProfileMapper {

    // 전달된 id를 데이터베이스 테이블에서 조회를해서, UserProfile이라는 객체를 리턴하는 API
    @Select("SELECT * FROM UserProfile WHERE id = #{id}")
    UserProfile getUserProfile(@Param("id") int id);

    // UserProfile 테이블의 모든 사용자정보를 가져와서 List형태로 반환되는 SQL문
    @Select("SELECT * FROM UserProfile")
    List<UserProfile> getUserProfileList();

    // name,phone,address의 데이터 삽입(생성)하는 SQL문
    @Insert("INSERT INTO UserProfile (name, phone, address) VALUES (#{name}, #{phone}, #{address})")
    int insertUserProfile(@Param("name") String name, @Param("phone") String phone, @Param("address") String address);

    // 전달된 id의 UserProfile의 name,phone,address의 데이터를 수정하는 SQL문 (데이터 수정)
    @Update("UPDATE UserProfile SET name=#{name}, phone=#{phone}, address=#{address} WHERE id=#{id}")
    int updateUserProfile(@Param("id") int id, @Param("name") String name, @Param("phone") String phone, @Param("address") String address);

    // 전달된 id에 맞는 UserProfile의 정보를 삭제하는 SQL문
    @Delete("DELETE FROM UserProfile WHERE id=#{id}")
    int deleteUserProfile(@Param("id") int id);
}
