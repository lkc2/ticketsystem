package com.example.ticketsystem.controller;

import com.example.ticketsystem.entity.User;
import com.example.ticketsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController 代表這是一個 REST API 控制器
@RestController
@RequestMapping("/api/users")  // API 路徑開頭
public class UserController {

    // 自動注入 Service
    @Autowired
    private UserService userService;

    // 取得所有用戶
    @GetMapping
    public List<User> getAllUsers() {
        return userService.list();
    }

    // 根據 ID 查詢用戶
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    // 新增用戶
    @PostMapping
    public boolean createUser(@RequestBody User user) {
        return userService.save(user);
    }

    // 修改用戶資料
    @PutMapping("/{id}")
    public boolean updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return userService.updateById(user);
    }

    // 刪除用戶
    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        return userService.removeById(id);
    }
}
