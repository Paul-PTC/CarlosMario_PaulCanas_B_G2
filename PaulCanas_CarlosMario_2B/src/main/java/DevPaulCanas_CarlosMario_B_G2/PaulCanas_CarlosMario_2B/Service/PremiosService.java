package DevPaulCanas_CarlosMario_B_G2.PaulCanas_CarlosMario_2B.Service;

import DevPaulCanas_CarlosMario_B_G2.PaulCanas_CarlosMario_2B.Entity.PeliculaEntity;
import DevPaulCanas_CarlosMario_B_G2.PaulCanas_CarlosMario_2B.Entity.PremiosEntity;
import DevPaulCanas_CarlosMario_B_G2.PaulCanas_CarlosMario_2B.Exeptions.ExeptionNotFound;
import DevPaulCanas_CarlosMario_B_G2.PaulCanas_CarlosMario_2B.Models.DTO.PremiosDTO;
import DevPaulCanas_CarlosMario_B_G2.PaulCanas_CarlosMario_2B.Repository.PeliculaRepository;
import DevPaulCanas_CarlosMario_B_G2.PaulCanas_CarlosMario_2B.Repository.PremiosRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@CrossOrigin
public class PremiosService {
    @Autowired
    private PremiosRepository repo;
    @Autowired
    private PeliculaRepository repoC;

    private PremiosDTO convertirADTO (PremiosEntity objEntity){
        PremiosDTO dto = new PremiosDTO();
        dto.setID_PREMIO(objEntity.getID_PREMIO());
        dto.setIdPelicula(objEntity.getPelicula().getIdPelicula());
        dto.setNOMBRE_PREMIO(objEntity.getNOMBRE_PREMIO());
        dto.setCATEGORIA(objEntity.getCATEGORIA());
        dto.setANO_PREMIO(objEntity.getANO_PREMIO());
        dto.setRESULTADO(objEntity.getRESULTADO());
        dto.setFECHA_REGISTRO(objEntity.getFECHA_REGISTRO());
        return dto;
    }

    private PremiosEntity convertirAEntity (PremiosDTO json){
        PremiosEntity entity = new PremiosEntity();
        entity.setID_PREMIO(json.getID_PREMIO());

        PeliculaEntity pelis =  repoC.findById(json.getIdPelicula()).orElseThrow(()-> new RuntimeException("Pelicula no encontrada elija otra"));
        entity.setPelicula(pelis);
        entity.setNOMBRE_PREMIO(json.getNOMBRE_PREMIO());
        entity.setCATEGORIA(json.getCATEGORIA());
        entity.setANO_PREMIO(json.getANO_PREMIO());
        entity.setRESULTADO(json.getRESULTADO());
        entity.setFECHA_REGISTRO(json.getFECHA_REGISTRO());
        return entity;
    }

    public List<PremiosDTO> getAllPedidos(){
        List<PremiosEntity> pageEntity = repo.findAll();
        return pageEntity.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public PremiosDTO insert(@Valid PremiosDTO jsonData) {
        if (jsonData == null){
            throw new IllegalArgumentException("La categoria no puede ser nula");
        }
        try {
            PremiosEntity categoryEntity = convertirAEntity(jsonData);
            PremiosEntity categorySave = repo.save(categoryEntity);
            return convertirADTO(categorySave);
        }catch (Exception e){
            log.error("Error al registrar categoría: " + e.getMessage());
            throw new ExeptionNotFound("Error al registrar la categoría: " + e.getMessage());
        }
    }

    public PremiosDTO update(Long id, @Valid PremiosDTO jsonDTO) {
        if (jsonDTO == null){
            throw new IllegalArgumentException("El pedido no puede ser nulo");
        }
        PremiosEntity premiosData = repo.findById(id)
                .orElseThrow(() -> new ExeptionNotFound("Pedido no encontrado"));
        PeliculaEntity peli = repoC.findById(jsonDTO.getIdPelicula())
                .orElseThrow(() -> new ExeptionNotFound("Pelicula no encontrado"));
        premiosData.setPelicula(peli);
        premiosData.setNOMBRE_PREMIO(jsonDTO.getNOMBRE_PREMIO());
        premiosData.setCATEGORIA(jsonDTO.getCATEGORIA());
        premiosData.setANO_PREMIO(jsonDTO.getANO_PREMIO());
        premiosData.setRESULTADO(jsonDTO.getRESULTADO());
        premiosData.setFECHA_REGISTRO(jsonDTO.getFECHA_REGISTRO());

        PremiosEntity pedidoUpdate = repo.save(premiosData);

        return convertirADTO(pedidoUpdate);
    }

    public boolean delete(Long id) {
        try{
            PremiosEntity objEntity = repo.findById(id).orElse(null);
            if (objEntity != null){
                repo.deleteById(id);
                return true;
            }
            return false;
        }catch (EmptyResultDataAccessException e){
            throw new EmptyResultDataAccessException("No se encontró categoría con ID: "+id,1);
        }
    }

    public PremiosDTO findById(Long idPremio) {
        PremiosEntity premio = repo.findById(idPremio)
                .orElseThrow(() -> new ExeptionNotFound("Premio no encontrado"));

        return convertirADTO(premio);
    }

    public PremiosDTO findByNombre(String nombre) {
        PremiosEntity premio = repo.findByNombre(nombre)
                .orElseThrow(() -> new ExeptionNotFound("Nombre no encontrado"));

        return convertirADTO(premio);
    }

    public PremiosDTO findByCategoria(String nombre) {
        PremiosEntity premio = repo.findByCategoria(nombre)
                .orElseThrow(() -> new ExeptionNotFound("Nombre no encontrado"));

        return convertirADTO(premio);
    }
}
