package com.doe.doeconect.model.repository;

import com.doe.doeconect.model.entity.Doador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoadorRepository extends JpaRepository<Doador, Long> {
    Optional<Doador> findByUsuarioId(Long usuarioId);
}
