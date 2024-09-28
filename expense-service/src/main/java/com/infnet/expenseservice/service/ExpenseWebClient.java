package com.infnet.expenseservice.service;

import com.infnet.expenseservice.model.Expense;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ExpenseWebClient {

    private final WebClient webClient;

    public ExpenseWebClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8084/api/expenses").build();
    }

    public Flux<Expense> findAll() {
        return webClient.get()
                .retrieve()
                .bodyToFlux(Expense.class);
    }

    public Mono<Expense> findById(Long id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Expense.class);
    }

    public Mono<Expense> save(Expense expense) {
        return webClient.post()
                .bodyValue(expense)
                .retrieve()
                .bodyToMono(Expense.class);
    }

    public Mono<Void> delete(Long id) {
        return webClient.delete()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
