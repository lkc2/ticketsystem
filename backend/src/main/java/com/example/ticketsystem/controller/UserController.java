package com.example.ticketsystem.controller;

import com.example.ticketsystem.entity.User;
import com.example.ticketsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")  //API路徑開頭
public class UserController {

    // 自動注入 Service
    @Autowired
    private UserService userService;

    // 登入
    @PostMapping("/login")
    public User login(@RequestBody User req) {
        User user = userService.findByUsername(req.getUsername());
        if (user == null) throw new RuntimeException("用戶不存在");
        if (!user.getPassword().equals(req.getPassword())) throw new RuntimeException("密碼錯誤");
        return user;
    }

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
    public User createUser(@RequestBody User user) {
        userService.save(user);
        return user;
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
        boolean result = userService.removeById(id);
        System.out.println("刪除用戶 id = " + id + ", 結果 = " + result);
        return result;
    }

}
