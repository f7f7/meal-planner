package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Constants;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.User;
import com.example.entity.UserDTO;
import com.example.exception.CustomException;
import com.example.mapper.UserMapper;
import com.example.utils.TokenUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;


import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;
    private final ObjectMapper mapper = new ObjectMapper(); // Jackson 的 ObjectMapper




    /**
     * add 新增
     * when adding users, updated columns: username, role(, password, name)
     */
    public void add(User user) {
        User dbUser = userMapper.selectByUsername(user.getUsername());
        if (ObjectUtil.isNotNull(dbUser)) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        if (ObjectUtil.isEmpty(user.getPassword())) {
            user.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }
        if (ObjectUtil.isEmpty(user.getName())) {
            user.setName(user.getUsername());
        }
//        user.setAccount(0D);
        /** default meal times can be set here
         * user.set
         * */
        user.setRole(RoleEnum.USER.name());
        userMapper.insert(user);
    }

    public void add(UserDTO userDTO) {
        User user = convertDtoToUser(userDTO);
        User dbUser = userMapper.selectByUsername(user.getUsername());
        if (ObjectUtil.isNotNull(dbUser)) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        if (ObjectUtil.isEmpty(user.getPassword())) {
            user.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }
        if (ObjectUtil.isEmpty(user.getName())) {
            user.setName(user.getUsername());
        }
        user.setRole(RoleEnum.USER.name());
        userMapper.insert(user);
    }


    /**
     * 删除
     */
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            userMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(User user) {
        userMapper.updateById(user);
    }

    public void updateById(UserDTO userDTO) {
        User user = convertDtoToUser(userDTO);
        userMapper.updateById(user);
    }



    /**
     * 根据ID查询
     */
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<User> selectAll(User user) {
        return userMapper.selectAll(user);
    }

    public List<User> selectAll(UserDTO userDTO) {
        User user = convertDtoToUser(userDTO);
        return userMapper.selectAll(user);
    }

    /**
     * 分页查询
     */
    public PageInfo<UserDTO> selectPage(User user, Integer pageNum, Integer pageSize) throws IOException {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.selectAll(user);
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user1 : list) {
            userDTOList.add(new UserDTO(user1));
        }
        return PageInfo.of(userDTOList);
    }

    /**
    * register user
    */
    public void register(Account account) {
        User user = new User();
        BeanUtils.copyProperties(account, user);
        add(user);

    }

    /**
     * login user
     * */
    public Account login(Account account) {
        Account dbUser = userMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbUser)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbUser.getPassword())) {
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }
        //generate token
        String tokenData = dbUser.getId() + "-" + RoleEnum.USER.name();
        String token = TokenUtils.createToken(tokenData, dbUser.getPassword());
        dbUser.setToken(token);
        return dbUser;
    }

    /**
     * update user's pwd
     * */
    public void updatePassword(Account account) {
        User dbUser = userMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbUser)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbUser.getPassword())) {
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }
        dbUser.setPassword(account.getNewPassword());
        userMapper.updateById(dbUser);
    }




    /**
     * String(Json) and Map conversion
     * */
    public Map<String, String> convertMealTimesToMap(User user) throws IOException {
        // 将Json字符串转换为 Map
        return mapper.readValue(user.getMealTimes(), new TypeReference<>() {});
    }

    public void convertMealTimesToString(User user, Map<String, String> mealTimes) throws JsonProcessingException {
        // 将 Map 转换为 JSON 字符串
        String json = mapper.writeValueAsString(mealTimes);
        user.setMealTimes(json);
    }




/**
 * UserDTO -> User
 * */
    private User convertDtoToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        user.setPhone(userDTO.getPhone());
        user.setEmail(userDTO.getEmail());
        user.setAvatar(userDTO.getAvatar());
        user.setRole(userDTO.getRole());
        user.setLocation(userDTO.getLocation());


        // 将 mealTimes 转换为 JSON 字符串
        //****** 仅当 mealTimes 不为空时转换为 JSON 并设置
        if (userDTO.getMealTimes() != null) {
            try {
                String mealTimesJson = mapper.writeValueAsString(userDTO.getMealTimes());
                user.setMealTimes(mealTimesJson);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error converting mealTimes to JSON", e);
            }
        }


        return user;
    }


}
