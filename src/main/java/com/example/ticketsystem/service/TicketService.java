package com.example.ticketsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ticketsystem.entity.Ticket;

import java.util.List;

public interface TicketService extends IService<Ticket> {
    // 查詢某用戶所有客服單
    List<Ticket> findTicketsByUserId(Long userId);

    // 查詢特定狀態的客服單
    List<Ticket> findTicketsByStatus(String status);

    // 關鍵字搜尋
    List<Ticket> searchTickets(String keyword);

    // 指派客服單
    boolean assignTicket(Long ticketId, Long agentId);
}
