package DevPaulCanas_CarlosMario_B_G2.PaulCanas_CarlosMario_2B.Entity;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "ADMIN.PELICULAS")
public class PeliculaEntity {

    @Id
    @Column(name = "ID_PELICULA")
    private Long IdPelicula;

    @OneToMany(mappedBy = "ADMIN.PELICULAS", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PremiosEntity> premios;

}
