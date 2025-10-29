package com.musabeli.api_laboratorio.repositories;

import com.musabeli.api_laboratorio.entities.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {


}
