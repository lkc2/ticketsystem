package com.example.ticketsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ticketsystem.entity.TicketReply;

import java.util.List;

public interface TicketReplyService extends IService<TicketReply> {
    // 查詢某張客服單所有回覆
    List<TicketReply> findRepliesByTicketId(Long ticketId);

    // 新增回覆（也可以直接用 save）
    TicketReply addReply(Long ticketId, Long userId, String content);
}
