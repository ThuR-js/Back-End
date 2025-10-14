package com.doe.doeconect.model.repository;

import com.doe.doeconect.model.entity.Condicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CondicaoRepository extends JpaRepository<Condicao, Long> {
}
