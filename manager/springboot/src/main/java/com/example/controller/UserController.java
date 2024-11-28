package com.example.controller;

import com.example.common.Result;
import com.example.entity.User;
import com.example.entity.UserDTO;
import com.example.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * add user
     * user/ userdto???
     */
//    @PostMapping("/add")
//    public Result add(@RequestBody User user) {
//        userService.add(user);
//        return Result.success();
//    }

    @PostMapping("/add")
    public Result add(@RequestBody UserDTO userDTO) throws IOException {
        userService.add(userDTO);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        userService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
//    @PutMapping("/update")
//    public Result updateById(@RequestBody User user) {
//        userService.updateById(user);
//        return Result.success();
//    }
    @PutMapping("/update")
    public Result updateById(@RequestBody UserDTO userDTO) throws IOException {
        userService.updateById(userDTO);
        return Result.success();
    }





    /**
     * search user by id
     * mealTimes already in format of Map
     *
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) throws IOException {
        User user = userService.selectById(id);
        UserDTO userDTO = new UserDTO(user);
//        return Result.success(user);
        return Result.success(userDTO);
    }

    /**
     * search all users
     * mealTimes already formatted in Map
     *
     */
    @GetMapping("/selectAll")
    public Result selectAll(UserDTO userDTO) throws IOException {

        List<User> list = userService.selectAll(userDTO);
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user1 : list) {
            userDTOList.add(new UserDTO(user1));
        }
        return Result.success(userDTOList);

//        List<User> list = userService.selectAll(user);
//        return Result.success(list);
    }

    /**
     * page query
     * mealTimes already formatted in Map
     */
    @GetMapping("/selectPage")
    public Result selectPage(User user,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) throws IOException {
        PageInfo<UserDTO> page = userService.selectPage(user, pageNum, pageSize);
        return Result.success(page);
    }


    /**
     * get property of meal times
     * */
    @GetMapping("/meal-times/{id}")
    public Result getMealTimes(@PathVariable Integer id) throws IOException {
        User user = userService.selectById(id);
//        return Result.success(user.getMealTimes());
        return Result.success(new UserDTO(user).getMealTimes());
//        return Result.success(userService.convertMealTimesToMap(user));
    }


}