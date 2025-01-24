package br.com.wsp.transfer.controller.v1;

import br.com.wsp.transfer.dto.TransferDto;
import br.com.wsp.transfer.service.ITransferService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @PostMapping
    public ResponseEntity<EntityModel<TransferDto>> save(@RequestBody @Valid TransferDto transferDto) {

        Optional<TransferDto> saved = service.save(transferDto);

        EntityModel<TransferDto> model = EntityModel.of(
                saved.get(), linkTo(methodOn(TransferControllerV1.class)
                        .findById(saved.get().getId())
                        ).withSelfRel());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id")
                .buildAndExpand(saved.get().getId())
                .toUri();

        return ResponseEntity.created(location).body(model);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<TransferDto>> findById(@PathVariable @Valid @NotNull UUID id) {

        Optional<TransferDto> byId = service.findById(id);

        EntityModel<TransferDto> entityModel = EntityModel.of(byId.get());
        entityModel.add(linkTo(methodOn(TransferControllerV1.class).findById(byId.get().getId())).withSelfRel());

        return ResponseEntity.ok(entityModel);
    }


    @GetMapping
    public CollectionModel<EntityModel<TransferDto>> getTransfers(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<TransferDto> transfersPage = service.findAll(PageRequest.of(page, size));

        List<EntityModel<TransferDto>> transfers = transfersPage.getContent().stream()
                .map(transfer -> EntityModel.of(transfer,
                        linkTo(methodOn(TransferControllerV1.class).getTransfers(page, size)).withSelfRel()))
                .toList();

        return CollectionModel.of(transfers,
                linkTo(methodOn(TransferControllerV1.class).getTransfers(page, size)).withSelfRel());
    }
}