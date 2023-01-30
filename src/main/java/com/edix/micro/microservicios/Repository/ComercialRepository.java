package com.edix.micro.microservicios.Repository;

import com.edix.micro.microservicios.EntityBeans.Comerciale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComercialRepository extends JpaRepository<Comerciale, Integer>{
}
