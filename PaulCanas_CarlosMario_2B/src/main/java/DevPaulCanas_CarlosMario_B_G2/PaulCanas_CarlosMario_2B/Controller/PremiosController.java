package DevPaulCanas_CarlosMario_B_G2.PaulCanas_CarlosMario_2B.Controller;

import DevPaulCanas_CarlosMario_B_G2.PaulCanas_CarlosMario_2B.Exeptions.ExeptionNotFound;
import DevPaulCanas_CarlosMario_B_G2.PaulCanas_CarlosMario_2B.Models.DTO.PremiosDTO;
import DevPaulCanas_CarlosMario_B_G2.PaulCanas_CarlosMario_2B.Service.PremiosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/premios")
@CrossOrigin
public class PremiosController {
    @Autowired
    private PremiosService services;

    @GetMapping("/list")
    public ResponseEntity<List<PremiosDTO>> getList() {
        return ResponseEntity.ok(services.getAllPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PremiosDTO> getPremioById(@PathVariable Long id) {
        return ResponseEntity.ok(services.findById(id));
    }

    @PostMapping("/newPremio")
    public ResponseEntity<Map<String, Object>> insert(@Valid @RequestBody PremiosDTO json) {
        try {
            PremiosDTO response = services.insert(json);
            if (response == null) {
                return ResponseEntity.badRequest().body(Map.of(
                        "error", "Inserción incorrecta",
                        "estatus", "Inserción incorrecta",
                        "descripcion", "Verifique los valores"
                ));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "estado", "Completado",
                    "data", response
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "error",
                    "message", "Error al registrar Premio",
                    "detail", e.getMessage()
            ));
        }
    }

    @PutMapping("/updatePremio/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @Valid @RequestBody PremiosDTO pedidoDTO,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }

        try {
            PremiosDTO pedidoActualizado = services.update(id, pedidoDTO);
            return ResponseEntity.ok(pedidoActualizado);
        } catch (ExeptionNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "error", e.getMessage()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "error", "Error inesperado",
                    "detail", e.getMessage()
            ));
        }
    }


    @DeleteMapping("/deletePremio/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        try {
            if (!services.delete(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .header("X-Mensaje-Error", "Pedido no encontrado")
                        .body(Map.of(
                                "error", "Not found",
                                "mensaje", "El premio no ha sido encontrado",
                                "timestamp", Instant.now().toString()
                        ));
            }

            return ResponseEntity.ok(Map.of(
                    "status", "Proceso completado",
                    "message", "premio eliminado exitosamente"
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "status", "Error",
                    "message", "Error al eliminar el premio",
                    "detail", e.getMessage()
            ));
        }
    }
}







