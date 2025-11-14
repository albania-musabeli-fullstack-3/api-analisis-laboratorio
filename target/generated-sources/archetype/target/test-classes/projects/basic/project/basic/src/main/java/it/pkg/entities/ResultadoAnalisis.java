package it.pkg.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "RESULTADO_ANALISIS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultadoAnalisis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaAnalisis;

    @Column(nullable = false)
    private String nombreAnalisis;

    @Column(nullable = false)
    private String resultado;

    @Column(nullable = false)
    private String observaciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_LABORATORIO", nullable = false)
    private Laboratorio laboratorio;
}
