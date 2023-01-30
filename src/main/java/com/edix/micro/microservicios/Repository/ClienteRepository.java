package com.edix.micro.microservicios.Repository;

import com.edix.micro.microservicios.EntityBeans.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
}
