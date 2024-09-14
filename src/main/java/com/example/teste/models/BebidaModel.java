package com.example.teste.models;

import jakarta.persistence.*;

import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_bebidas")
public class BebidaModel extends RepresentationModel<BebidaModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idBebida;
    private String name;
    private BigDecimal value;

    @Column(columnDefinition = "TEXT")
    private String description;

    public UUID getIdBebida() {
        return idBebida;
    }

    public void setIdBebida(UUID idBebida) {
        this.idBebida = idBebida;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
