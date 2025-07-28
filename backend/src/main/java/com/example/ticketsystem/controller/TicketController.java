package com.example.ticketsystem.controller;

import com.example.ticketsystem.entity.Ticket;
import com.example.ticketsystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// RESTful 控制器，路徑為 /api/tickets
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // 取得所有客服單
    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.list();
    }

    // 取得單一客服單（依 ID）
    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable Long id) {
        return ticketService.getById(id);
    }

    // 查詢某用戶的所有客服單
    @GetMapping("/user/{userId}")
    public List<Ticket> getTicketsByUserId(@PathVariable Long userId) {
        return ticketService.findTicketsByUserId(userId);
    }

    // 依狀態查詢客服單（如 open/done）
    @GetMapping("/status/{status}")
    public List<Ticket> getTicketsByStatus(@PathVariable String status) {
        return ticketService.findTicketsByStatus(status);
    }

     // 關鍵字搜尋客服單（標題、內容）
    @GetMapping("/search")
    public List<Ticket> searchTickets(@RequestParam String keyword) {
        return ticketService.searchTickets(keyword);
    }

    // 新增客服單
    @PostMapping
    public boolean createTicket(@RequestBody Ticket ticket) {
        return ticketService.save(ticket);
    }

    // 修改客服單
    @PutMapping("/{id}")
    public boolean updateTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
        ticket.setId(id);
        return ticketService.updateById(ticket);
    }

    // 刪除客服單
    @DeleteMapping("/{id}")
    public boolean deleteTicket(@PathVariable Long id) {
        return ticketService.removeById(id);
    }
}
