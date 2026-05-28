package com.doe.doeconect.model.repository;

import com.doe.doeconect.model.entity.AnaliseAdministrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnaliseAdministradorRepository extends JpaRepository<AnaliseAdministrador, Long> {
    List<AnaliseAdministrador> findByAnuncioId(Long anuncioId);
}
