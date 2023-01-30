package com.edix.micro.microservicios.DAO;

import com.edix.micro.microservicios.EntityBeans.Comerciale;

import java.util.List;
import java.util.Optional;

public interface IntComercialDao {
    List<Comerciale>buscarTodos();
    Optional<Comerciale> buscarPorId(int id);
    Comerciale insertar(Comerciale comercial);
    Comerciale actualizar(Comerciale comercial);
    boolean eliminar(int idComercial);

}
