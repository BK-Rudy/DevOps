package com.infnet.vehicleservice.controller;

import com.infnet.vehicleservice.model.Vehicle;
import com.infnet.vehicleservice.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @Operation(summary = "Busca todos os veículos", description = "Retorna a lista de todos os veículos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de veículos encontrada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vehicle.class))),
            @ApiResponse(responseCode = "400", description = "Erro na busca dos veículos", content = @Content)
    })
    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(vehicleService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Busca o veículo pelo ID", description = "Busca um veículo pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vehicle.class))),
            @ApiResponse(responseCode = "404", description = "Erro na busca do veículo", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(vehicleService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Busca veículos pelo modelo", description = "Busca todos os veículos que correspondem ao modelo fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículos encontrados com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vehicle.class))),
            @ApiResponse(responseCode = "400", description = "Erro na busca dos veículos", content = @Content)
    })
    @GetMapping("/model/{model}")
    public ResponseEntity<?> findByModel(@PathVariable String model) {
        try {
            return new ResponseEntity<>(vehicleService.findByModel(model), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Cadastra um novo veículo", description = "Cadastra um novo veículo com base nos parâmetros fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Veículo cadastrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vehicle.class))),
            @ApiResponse(responseCode = "400", description = "Erro no cadastro do veículo", content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Vehicle params) {
        try {
            return new ResponseEntity<>(vehicleService.save(params), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Atualiza um veículo existente", description = "Atualiza as informações de um veículo pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vehicle.class))),
            @ApiResponse(responseCode = "400", description = "Erro na atualização do veículo", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Vehicle params) {
        try {
            return new ResponseEntity<>(vehicleService.update(id, params), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Deleta um veículo", description = "Deleta um veículo com base no seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo deletado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vehicle.class))),
            @ApiResponse(responseCode = "404", description = "Erro ao deletar o veículo", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(vehicleService.delete(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
