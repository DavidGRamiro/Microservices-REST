package com.edix.micro.microservicios.Repository;

import com.edix.micro.microservicios.EntityBeans.Comerciale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComercialRepository extends JpaRepository<Comerciale, Integer>{

    //Devolver una lista de comerciales que han atendido pedidos de un cliente
    //que coincida por ese ID.
    @Query(
            value = "select  * from Comerciales c join Pedidos p on c.id_comercial = p.id_comercial where p.id_cliente =?1",
            nativeQuery = true
    )
    List<Comerciale> findByClienteId(int id);

    //Devolver una lista de los comerciales que han atendido algún pedido
    @Query("select distinct c from Comerciale c, Pedido p where p.idComercial = c")
    List<Comerciale> conPedidos();
}
