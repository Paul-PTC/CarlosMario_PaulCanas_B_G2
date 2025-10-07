package DevPaulCanas_CarlosMario_B_G2.PaulCanas_CarlosMario_2B.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "ADMIN.PREMIOS")
public class PremiosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_PREMIOS")
    @SequenceGenerator(name = "SEQ_PREMIOS", sequenceName = "SEQ_PREMIOS", allocationSize = 1)
    @Column(name = "ID_PREMIO")
    private Long ID_PREMIO;

    @Column(name = "NOMBRE_PREMIO", nullable = false, length = 120)
    @NotBlank(message = "No puede ser nulo")
    private String NOMBRE_PREMIO;

    @Column(name = "CATEGORIA", nullable = false, length = 120)
    @NotBlank(message = "No puede ser nulo")
    private String CATEGORIA;

    @Column(name = "ANO_PREMIO", nullable = false, length = 4)
    @NotBlank(message = "No puede ser nulo")
    private String ANO_PREMIO;

    @Column(name = "RESULTADO", nullable = false, length = 120)
    @NotBlank(message = "No puede ser nulo")
    private String RESULTADO;

    @Column(name = "FECHA_REGISTRO", nullable = false)
    @NotBlank(message = "No puede ser nulo")
    private LocalDateTime FECHA_REGISTRO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PELICULA", nullable = false)
    private PeliculaEntity pelicula;
}
