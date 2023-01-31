package com.edix.micro.microservicios.Repository;

import com.edix.micro.microservicios.EntityBeans.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {

    //Query para sacar la lista de pedidos a los que ha atendido un comercial, y al cliente al que pertence.
    @Query(
            value = "SELECT * FROM Pedidos where id_comercial = ?1",
            nativeQuery = true
    )
    List<Pedido> pedidosGestionadosPorComercial(int idComercial);
}
