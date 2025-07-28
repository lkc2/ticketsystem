package com.example.ticketsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ticketsystem.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    // 依帳號查詢使用者
    User findByUsername(String username);

    // 查詢所有客服人員
    List<User> findAllAgents();

    // 重設密碼
    boolean resetPassword(Long userId, String newPassword);
}
