package com.example.crud.model;


public class UserProfile {
    private int id;
    private String name;
    private String phone;
    private String address;

    // id, name, phone, address를 파라미터로 받아서 해당되는 필드를 채워주는 생성자를 만듬
    public UserProfile(int id, String name, String phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    // getId, setId를 통해 id라는 멤버변수의 값을 얻어오기도하고,값을 세팅하기도하는 게터,세터
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
