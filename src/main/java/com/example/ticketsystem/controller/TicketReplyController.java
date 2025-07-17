package com.example.ticketsystem.controller;

import com.example.ticketsystem.entity.TicketReply;
import com.example.ticketsystem.service.TicketReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// RESTful 控制器，路徑為 /api/replies
@RestController
@RequestMapping("/api/replies")
public class TicketReplyController {

    @Autowired
    private TicketReplyService ticketReplyService;

    // 取得所有回覆（不常用，但給管理員全查）
    @GetMapping
    public List<TicketReply> getAllReplies() {
        return ticketReplyService.list();
    }

    // 取得某張客服單的所有回覆
    @GetMapping("/ticket/{ticketId}")
    public List<TicketReply> getRepliesByTicketId(@PathVariable Long ticketId) {
        return ticketReplyService.findRepliesByTicketId(ticketId);
    }

    // 新增回覆（用戶、客服都可用）
    @PostMapping
    public TicketReply addReply(@RequestBody TicketReply ticketReply) {
        ticketReplyService.save(ticketReply);
        return ticketReply;
    }

    // 可加修改/刪除功能（視權限決定是否公開 API）

    // 依回覆ID查詢單一回覆
    @GetMapping("/{id}")
    public TicketReply getReplyById(@PathVariable Long id) {
        return ticketReplyService.getById(id);
    }

    // 刪除回覆
    @DeleteMapping("/{id}")
    public boolean deleteReply(@PathVariable Long id) {
        return ticketReplyService.removeById(id);
    }
}
