package com.edix.micro.microservicios.DAO;

import com.edix.micro.microservicios.EntityBeans.Pedido;

import java.util.List;

public interface IntPedidoDao {
    List<Pedido> gestionPorComercial(int idComercial);
}
