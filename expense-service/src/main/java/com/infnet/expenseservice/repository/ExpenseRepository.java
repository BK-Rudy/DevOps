package com.infnet.expenseservice.repository;

import com.infnet.expenseservice.model.Expense;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends R2dbcRepository<Expense, Long> {}
