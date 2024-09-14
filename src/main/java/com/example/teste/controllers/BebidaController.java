package com.example.teste.controllers;

import com.example.teste.dtos.BebidaRecordDto;
import com.example.teste.models.BebidaModel;
import com.example.teste.repositories.BebidaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class BebidaController {

    @Autowired
    BebidaRepository BebidaRepository;

    @PostMapping("/bebidas")
    public ResponseEntity<BebidaModel>saveProduct(@RequestBody @Valid BebidaRecordDto BebidaRecordDto) {
        var BebidaModel = new BebidaModel();
        BeanUtils.copyProperties(BebidaRecordDto, BebidaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(BebidaRepository.save(BebidaModel));
    }

    @GetMapping("/bebidas")
    public ResponseEntity<List<BebidaModel>> getALLProducts() {
        List<BebidaModel> productsList = BebidaRepository.findAll();
        if (!productsList.isEmpty()) {
            for (BebidaModel product : productsList) {
               UUID id = product.getIdBebida();
               product.add(linkTo(methodOn(BebidaController.class).getOneProduct(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(productsList);
    }

    @GetMapping("/bebidas/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id) {
        Optional<BebidaModel> product = BebidaRepository.findById(id);
        if (product.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Bebida não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        product.get().add(linkTo(methodOn(BebidaController.class).getALLProducts()).withRel("Lista de Produtos"));
        return ResponseEntity.status(HttpStatus.OK).body(product.get());
    }

    @PutMapping("/bebidas/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid BebidaRecordDto BebidaRecordDto) {
        Optional<BebidaModel> product = BebidaRepository.findById(id);
        if (product.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Bebida não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        var BebidaModel = product.get();
        BeanUtils.copyProperties(BebidaRecordDto, BebidaModel);
        return ResponseEntity.status(HttpStatus.OK).body(BebidaRepository.save(BebidaModel));
    }

    @DeleteMapping("/bebidas/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id) {
        Optional<BebidaModel> product = BebidaRepository.findById(id);
        if (product.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Bebida não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        BebidaRepository.delete(product.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
