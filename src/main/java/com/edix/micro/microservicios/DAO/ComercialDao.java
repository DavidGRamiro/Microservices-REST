package com.edix.micro.microservicios.DAO;

import com.edix.micro.microservicios.EntityBeans.Comerciale;
import com.edix.micro.microservicios.Repository.ComercialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComercialDao implements IntComercialDao{

    @Autowired
    ComercialRepository cdao;
    @Override
    public List<Comerciale> buscarTodos(){
        return cdao.findAll();
    }

    @Override
    public Optional<Comerciale> buscarPorId(int id) {
        return cdao.findById(id);
    }

    @Override
    public Comerciale insertar(Comerciale comercial) {

        try{
            return cdao.save(comercial);
        }catch(Exception e){
            return null;
        }
    }
    @Override
    public Comerciale actualizar(Comerciale comercial) {

    try {
        if(buscarPorId(comercial.getId()).isPresent())
            return cdao.save(comercial);
    }catch(Exception e){
        return null;
        }
        return comercial;
    }
    @Override
    public boolean eliminar(int idComercial) {
        try{
            cdao.deleteById(idComercial);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public List<Comerciale> buscarPorIdCliente(int idCliente) {
        return cdao.findByClienteId(idCliente);
    }

    @Override
    public List<Comerciale> comercialConPedido() {
        return cdao.conPedidos();
    }
}

