package DevPaulCanas_CarlosMario_B_G2.PaulCanas_CarlosMario_2B.Models.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter @Getter
@ToString
@EqualsAndHashCode
public class PremiosDTO {

    private Long ID_PREMIO;
    private Long idPelicula;


    @NotBlank(message = "No puede ser nulo")
    @Size(min = 2, max = 119, message = "El nombre es muy largo")
    private String NOMBRE_PREMIO;

    @NotBlank(message = "No puede ser nulo")
    @Size(min = 2, max = 119, message = "El nombre es muy largo")
    private String CATEGORIA;

    @NotBlank(message = "No puede ser nulo")
    @Size(min = 4, max = 4, message = "El año no coincide")
    private String ANO_PREMIO;

    @NotBlank(message = "No puede ser nulo")
    @Size(min = 1, max = 20, message = "El Resultado es muy largo")
    private String RESULTADO;

    @NotBlank(message = "No puede ser nulo")
    private LocalDateTime FECHA_REGISTRO;
}
