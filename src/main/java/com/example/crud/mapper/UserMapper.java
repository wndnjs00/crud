package com.example.crud.mapper;

import com.example.crud.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

// @Mapper -> MyBatic가 인터페이스의 구현체를 자동생성
@Mapper
public interface UserMapper {

    // 회원가입
    @Insert("INSERT INTO User (email, password, name, phone, address) VALUES (#{email}, #{password}, #{name}, #{phone}, #{address})")
    int insertUser(@Param("email") String email, @Param("password") String password, @Param("name") String name, @Param("phone") String phone, @Param("address") String address);

    // 로그인 (이메일과 비밀번호로 사용자 검색)
    @Select("SELECT * FROM User WHERE email = #{email} AND password = #{password}")
    User loginUser(@Param("email") String email, @Param("password") String password);

    // 로그인 유지하기위한 토큰값 저장을 위해
    @Select("SELECT * FROM User WHERE email = #{email}")
    User getUserByEmail(@Param("email") String email);
}
