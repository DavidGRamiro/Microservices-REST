package com.edix.micro.microservicios.RESTController;

import com.edix.micro.microservicios.DAO.PedidoDao;
import com.edix.micro.microservicios.EntityBeans.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    PedidoDao pdao;

    @GetMapping("/buscar/{id}")
    public List<Pedido> buscarPorComercial(@PathVariable("id") int id){
        return pdao.gestionPorComercial(id);
    }
}
