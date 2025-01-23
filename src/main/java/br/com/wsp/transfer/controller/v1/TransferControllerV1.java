package br.com.wsp.transfer.controller.v1;

import br.com.wsp.transfer.dto.TransferDto;
import br.com.wsp.transfer.service.ITransferService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
    public ResponseEntity<EntityModel<TransferDto>> save(@RequestBody TransferDto transferDto) {

        TransferDto schedulingSaved = service.save(transferDto);

        EntityModel<TransferDto> model = EntityModel.of(
                schedulingSaved, linkTo(methodOn(TransferControllerV1.class)
                        .findOne(schedulingSaved.getId())).withSelfRel());

        URI location =  ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id")
                .buildAndExpand(schedulingSaved.getId())
                .toUri();

        return ResponseEntity.created(location).body(model);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TransferDto> findOne(@PathVariable Long id) {

        return ResponseEntity.ok(new TransferDto());
    }


}
