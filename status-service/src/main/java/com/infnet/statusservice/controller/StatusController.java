package com.infnet.statusservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/status")
@Tag(name = "Get Status", description = "API para requisição HTTP GET de status")
public class StatusController {

    @Operation(summary = "Serviço ativo", description = "Verifica se o serviço está ativo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso, o serviço está ativo", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro, o serviço não está ativo", content = @Content)
    })
    @GetMapping
    public String getStatus() {
        return "Serviço está ativo";
    }
}