package com.infnet.expenseservice.controller;

import com.infnet.expenseservice.model.Expense;
import com.infnet.expenseservice.repository.ExpenseRepository;
import com.infnet.expenseservice.service.ExpenseWebClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    private ExpenseWebClient expenseWebClient;

    public ExpenseController(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Operation(summary = "Busca todas as despesas", description = "Retorna a lista de todos as despesas cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de despesas encontrada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Expense.class))),
            @ApiResponse(responseCode = "400", description = "Erro na busca das despesas", content = @Content)
    })
    @GetMapping
    public Flux<Expense> getAll() {
        return expenseRepository.findAll();
    }

    @Operation(summary = "Cadastra um nova despesa", description = "Cadastra uma nova despesa com base nos parâmetros fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Despesa cadastrada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Expense.class))),
            @ApiResponse(responseCode = "400", description = "Erro no cadastro da despesa", content = @Content)
    })
    @PostMapping
    public Mono<Expense> save(@RequestBody Expense expense) {
        return expenseRepository.save(expense);
    }
}
