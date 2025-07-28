package com.example.ticketsystem.service;

import com.example.ticketsystem.entity.TicketReply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime; // 加這行

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TicketReplyServiceTest {

    @Autowired
    private TicketReplyService ticketReplyService;

    @Test
    void testCreateReply() {
        TicketReply reply = new TicketReply();
        reply.setTicketId(1L);
        reply.setUserId(1L);
        reply.setContent("單元測試回覆" + System.currentTimeMillis());
        reply.setCreatedAt(LocalDateTime.now()); // ★修正這裡

        boolean saved = ticketReplyService.save(reply);
        assertTrue(saved, "回覆應該成功新增");
    }

    // ...（其他測試不用動）
}
