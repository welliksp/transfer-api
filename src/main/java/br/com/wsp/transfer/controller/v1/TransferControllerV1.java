package br.com.wsp.transfer.controller.v1;

import br.com.wsp.transfer.dto.TransferDto;
import br.com.wsp.transfer.service.ITransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/transfer")
public class TransferControllerV1 {

    private final ITransferService service;

    public TransferControllerV1(ITransferService service) {
        this.service = service;
    }


    @Operation(summary = "Cria uma nova transferência", description = "Cria uma transferência e retorna o recurso com os links de HATEOAS.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transferência criada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TransferDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos na requisição")
    })
    @PostMapping
    public ResponseEntity<EntityModel<TransferDto>> save(@RequestBody @Valid TransferDto transferDto) {
        Optional<TransferDto> saved = service.save(transferDto);

        if (saved.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        TransferDto transfer = saved.get();

        EntityModel<TransferDto> model = EntityModel.of(transfer,
                linkTo(methodOn(TransferControllerV1.class).findById(transfer.getId())).withSelfRel(),
                linkTo(methodOn(TransferControllerV1.class).findAll()).withRel("all-transfers"),
                linkTo(methodOn(TransferControllerV1.class).deleteById(transfer.getId())).withRel("delete-transfer-by-id")
        );

        URI location = linkTo(methodOn(TransferControllerV1.class).findById(transfer.getId())).toUri();

        return ResponseEntity.created(location).body(model);
    }

    @Operation(summary = "Consulta uma transferência por ID", description = "Retorna os detalhes de uma transferência específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transferência encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TransferDto.class))),
            @ApiResponse(responseCode = "404", description = "Transferência não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<TransferDto>> findById(@PathVariable @Valid @NotNull UUID id) {
        Optional<TransferDto> byId = service.findById(id);

        if (byId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        TransferDto transfer = byId.get();

        EntityModel<TransferDto> entityModel = EntityModel.of(transfer,
                linkTo(methodOn(TransferControllerV1.class).findById(transfer.getId())).withSelfRel(),
                linkTo(methodOn(TransferControllerV1.class).findAll()).withRel("all-transfers"),
                linkTo(methodOn(TransferControllerV1.class).deleteById(transfer.getId())).withRel("delete")
        );

        return ResponseEntity.ok(entityModel);
    }

    @Operation(summary = "Lista todas as transferências", description = "Retorna uma lista paginada de transferências.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transferências retornadas com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Parâmetros de paginação inválidos")
    })
    @GetMapping
    public CollectionModel<EntityModel<TransferDto>> findAll() {
        List<TransferDto> all = service.findAll();

        List<EntityModel<TransferDto>> transfers = all.stream()
                .map(transfer -> EntityModel.of(transfer,
                        linkTo(methodOn(TransferControllerV1.class).findById(transfer.getId())).withSelfRel(),
                        linkTo(methodOn(TransferControllerV1.class).deleteById(transfer.getId())).withRel("delete")
                ))
                .toList();

        return CollectionModel.of(transfers,
                linkTo(methodOn(TransferControllerV1.class).findAll()).withSelfRel()
        );
    }

    @Operation(summary = "Deleta uma transferência por ID", description = "Remove uma transferência do sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Transferência deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Transferência não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable @Valid @NotNull UUID id) {
        service.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}