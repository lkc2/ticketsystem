package com.example.ticketsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ticketsystem.entity.Ticket;
import com.example.ticketsystem.mapper.TicketMapper;
import com.example.ticketsystem.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket> implements TicketService {

    @Override
    public List<Ticket> findTicketsByUserId(Long userId) {
        return lambdaQuery().eq(Ticket::getUserId, userId).list();
    }

    @Override
    public List<Ticket> findTicketsByStatus(String status) {
        return lambdaQuery().eq(Ticket::getStatus, status).list();
    }

    @Override
    public List<Ticket> searchTickets(String keyword) {
        return lambdaQuery().like(Ticket::getTitle, keyword)
                            .or().like(Ticket::getContent, keyword)
                            .list();
    }

    @Override
    public boolean assignTicket(Long ticketId, Long agentId) {
        Ticket ticket = getById(ticketId);
        if (ticket == null) return false;
        // 這裡假設 ticket 物件有 agentId 欄位，如果有就 set，沒有可自行加上
        // ticket.setAgentId(agentId);
        return updateById(ticket);
    }
}
