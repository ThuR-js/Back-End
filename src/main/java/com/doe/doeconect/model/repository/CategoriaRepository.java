package com.doe.doeconect.model.repository;

import com.doe.doeconect.model.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository  extends JpaRepository<Categoria, Long> {
}
