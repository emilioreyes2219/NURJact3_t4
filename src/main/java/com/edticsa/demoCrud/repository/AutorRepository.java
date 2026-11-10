package com.edticsa.demoCrud.repository;

import com.edticsa.demoCrud.models.Autor; 
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}