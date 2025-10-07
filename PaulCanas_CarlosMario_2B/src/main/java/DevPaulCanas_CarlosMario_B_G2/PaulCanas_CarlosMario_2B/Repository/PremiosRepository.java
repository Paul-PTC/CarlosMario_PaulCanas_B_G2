package DevPaulCanas_CarlosMario_B_G2.PaulCanas_CarlosMario_2B.Repository;

import DevPaulCanas_CarlosMario_B_G2.PaulCanas_CarlosMario_2B.Entity.PremiosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PremiosRepository extends JpaRepository<PremiosEntity, Long> {
    Optional<PremiosEntity> findByNombre(String nombre);
    Optional<PremiosEntity> findByCategoria(String nombre);
    Optional<PremiosEntity> findById(Long idPremio);
}
