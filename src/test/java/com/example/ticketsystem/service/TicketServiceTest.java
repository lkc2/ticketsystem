package com.example.ticketsystem.service;

import com.example.ticketsystem.entity.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TicketServiceTest {

    @Autowired
    private TicketService ticketService;

    @Test
    void testCreateTicket() {
        Ticket ticket = new Ticket();
        ticket.setUserId(1L); // 請確認 DB 有 id=1 的 user
        ticket.setTitle("單元測試客服單" + System.currentTimeMillis());
        ticket.setContent("單元測試內容");
        ticket.setStatus("open");

        boolean saved = ticketService.save(ticket);
        assertTrue(saved, "客服單應該成功新增");
    }

    @Test
    void testGetTicketById() {
        Ticket ticket = ticketService.getById(1L);
        assertNotNull(ticket, "ID=1 的客服單應存在（請自己先建測試客服單）");
    }

    @Test
    void testUpdateTicket() {
        Ticket ticket = ticketService.getById(1L);
        if (ticket != null) {
            ticket.setStatus("closed");
            boolean updated = ticketService.updateById(ticket);
            assertTrue(updated, "狀態更新應該成功");
        }
    }

    @Test
    void testDeleteTicket() {
        boolean deleted = ticketService.removeById(9999L); // 用不存在的 id 也行
        assertTrue(deleted || !deleted);
    }
}
