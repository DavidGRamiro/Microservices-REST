package com.edix.micro.microservicios.Repository;

import com.edix.micro.microservicios.EntityBeans.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
}
