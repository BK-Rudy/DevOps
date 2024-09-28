package com.infnet.expenseservice.service;

import com.infnet.expenseservice.model.Expense;
import com.infnet.expenseservice.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Flux<Expense> findAll() {
        return expenseRepository.findAll();
    }

    public Mono<Expense> save(Expense expense) {
        return expenseRepository.save(expense);
    }
}
