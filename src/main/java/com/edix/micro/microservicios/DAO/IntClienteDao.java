package com.edix.micro.microservicios.DAO;

import com.edix.micro.microservicios.EntityBeans.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IntClienteDao {

    List<Cliente> buscarTodos();

    Cliente darAlta(Cliente cliente);

    boolean eliminarCliente(int id);

    boolean modificarDatos(Cliente cliente);

    Cliente buscarUno(int id);

}
