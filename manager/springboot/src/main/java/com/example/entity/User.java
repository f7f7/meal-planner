package com.example.entity;

import cn.hutool.core.convert.impl.MapConverter;
import cn.hutool.json.JSONConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.Serializable;
import java.util.Map;

public class User extends Account implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** 姓名 */
    private String name;
    /** 电话 */
    private String phone;
    /** 邮箱 */
    private String email;
    /** 头像 */
    private String avatar;
    /** 角色标识 */
    private String role;

    private String location;

    private String mealTimes;


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getAvatar() {
        return avatar;
    }

    @Override
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) { this.role = role; }

    public String getLocation() { return location; }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMealTimes() {
        return mealTimes;
    }


    public void setMealTimes(String mealTimes) {
        this.mealTimes = mealTimes;
    }


//    public User() {
//        super();
//    }
//    public  User(UserDTO userDTO) {
//        User user = new User();
//        user.setUsername(userDTO.getUsername());
//        user.setPassword(userDTO.getPassword());
//        user.setName(userDTO.getName());
//        user.setPhone(userDTO.getPhone());
//        user.setEmail(userDTO.getEmail());
//        user.setLocation(userDTO.getLocation());
//
//        // 将 mealTimes 转换为 JSON 字符串
//        try {
//            String mealTimesJson = Mapper.writeValueAsString(userDTO.getMealTimes());
//            user.setMealTimes(mealTimesJson);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException("Error converting mealTimes to JSON", e);
//        }
//
//    }
}
