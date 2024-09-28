package com.infnet.expenseservice;

import com.infnet.expenseservice.controller.ExpenseController;
import com.infnet.expenseservice.model.Expense;
import com.infnet.expenseservice.repository.ExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@WebFluxTest(ExpenseController.class)
public class ExpenseControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private ExpenseRepository expenseRepository;

    private Expense expense;

    @BeforeEach
    void setUp() {
        expense = new Expense(20L, "Despesa 1", "Teste", 100.0f, 5f, 1.2f, 2f, 5);
    }

    @Test
    public void testFindAll(){
        doReturn(Flux.just(expense)).when(expenseRepository).findAll();

        webClient.get().uri("/api/expenses")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Expense.class)
                .hasSize(1)
                .contains(expense);

        verify(expenseRepository, times(1)).findAll();
    }

    @Test
    public void testSave(){
        Expense expense = new Expense(null, null, "Teste", 100.0f, 5f, 1.2f, 2f, 5);
        Expense savedExpense = new Expense(21L, "Despesa 1", "Teste", 100.0f, 5f, 1.2f, 2f, 5);

        doReturn(Mono.just(savedExpense)).when(expenseRepository).save(expense);

        webClient.post().uri("/api/expenses")
                .bodyValue(expense)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Expense.class)
                .isEqualTo(savedExpense);
        verify(expenseRepository, times(1)).save(expense);
    }
}
