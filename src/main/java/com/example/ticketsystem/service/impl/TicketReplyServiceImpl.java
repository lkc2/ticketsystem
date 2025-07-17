package com.example.ticketsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ticketsystem.entity.TicketReply;
import com.example.ticketsystem.mapper.TicketReplyMapper;
import com.example.ticketsystem.service.TicketReplyService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketReplyServiceImpl extends ServiceImpl<TicketReplyMapper, TicketReply> implements TicketReplyService {

    @Override
    public List<TicketReply> findRepliesByTicketId(Long ticketId) {
        return lambdaQuery().eq(TicketReply::getTicketId, ticketId).list();
    }

    @Override
    public TicketReply addReply(Long ticketId, Long userId, String content) {
        TicketReply reply = new TicketReply();
        reply.setTicketId(ticketId);
        reply.setUserId(userId);
        reply.setContent(content);
        reply.setCreatedAt(LocalDateTime.now());
        save(reply);
        return reply;
    }
}
