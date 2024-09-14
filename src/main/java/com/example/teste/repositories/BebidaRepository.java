package com.example.teste.repositories;

import com.example.teste.models.BebidaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BebidaRepository extends JpaRepository<BebidaModel, UUID> {

}
