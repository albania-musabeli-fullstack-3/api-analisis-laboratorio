package it.pkg.repositories;


import it.pkg.entities.ResultadoAnalisis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoRepository extends JpaRepository<ResultadoAnalisis, Long> {
}
