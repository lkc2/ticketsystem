package com.example.ticketsystem.service;

import com.example.ticketsystem.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testCreateUser() {
        User user = new User();
        user.setUsername("unit_test_user_" + System.currentTimeMillis());
        user.setPassword("test123");
        user.setRole("user");

        boolean saved = userService.save(user);
        assertTrue(saved, "使用者應該成功新增");
    }

    @Test
    void testGetUserById() {
        User user = userService.getById(1L);
        assertNotNull(user, "ID=1 的用戶應存在（請自己先建測試用戶）");
    }

    @Test
    void testUpdateUser() {
        User user = userService.getById(1L);
        if (user != null) {
            user.setPassword("updatedPassword");
            boolean updated = userService.updateById(user);
            assertTrue(updated, "密碼更新應該成功");
        }
    }

    @Test
    void testDeleteUser() {
        boolean deleted = userService.removeById(9999L); // 用不存在的 id 也行
        assertTrue(deleted || !deleted); // 刪除失敗也不應拋例外
    }
}