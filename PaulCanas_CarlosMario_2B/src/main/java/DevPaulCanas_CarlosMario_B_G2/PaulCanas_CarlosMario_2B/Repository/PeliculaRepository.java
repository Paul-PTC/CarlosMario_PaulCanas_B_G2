package DevPaulCanas_CarlosMario_B_G2.PaulCanas_CarlosMario_2B.Repository;

import DevPaulCanas_CarlosMario_B_G2.PaulCanas_CarlosMario_2B.Entity.PeliculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<PeliculaEntity, Long> {

}
