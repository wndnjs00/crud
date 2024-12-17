package com.example.crud.model;

// 보통 안드로이드앱에서는 클래스의 멤버들을 public으로 정의하고 외부클래스에서 바로 멤버변수에 접근하는것이 일반적인 관례인데,
// 서버 애플리케이션을 개발할때는 멤버변수들을 모두 private으로해서 멤버변수에 접근해서 값을 세팅하거나 값을 얻어가기위한 별도의 게터,세터를 만드는것이 일반적인 관례임
public class UserProfile {
    private String id;
    private String name;
    private String phone;
    private String address;

    // id, name, phone, address를 파라미터로 받아서 해당되는 필드를 채워주는 생성자를 만듬
    public UserProfile(String id, String name, String phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }


    // 게터,세터가 모든변수에 만들어짐
    // getId, setId를 통해 id라는 멤버변수의 값을 얻어오기도하고,값을 세팅하기도하는 게터,세터
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
