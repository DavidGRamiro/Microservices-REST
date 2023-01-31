package com.edix.micro.microservicios.DAO;

import com.edix.micro.microservicios.EntityBeans.Pedido;
import com.edix.micro.microservicios.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PedidoDao implements IntPedidoDao{

    final
    PedidoRepository p;

    public PedidoDao(PedidoRepository p) {
        this.p = p;
    }

    @Override
    public List<Pedido> gestionPorComercial(int idComercial) {
        return p.pedidosGestionadosPorComercial(idComercial);
    }
}
