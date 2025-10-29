package com.musabeli.api_laboratorio.repositories;


import com.musabeli.api_laboratorio.entities.ResultadoAnalisis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoRepository extends JpaRepository<ResultadoAnalisis, Long> {
}
