package com.edix.micro.microservicios.DAO;

import com.edix.micro.microservicios.EntityBeans.Cliente;
import com.edix.micro.microservicios.Repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteDao implements IntClienteDao {

    final
    ClienteRepository cdao;

    public ClienteDao(ClienteRepository cdao) {
        this.cdao = cdao;
    }

    @Override
    public List<Cliente> buscarTodos() {
        return cdao.findAll();
    }

    @Override
    public Cliente darAlta(Cliente cliente) {

       if(buscarUno(cliente.getId()) == null)
           return cdao.save(cliente);
       return null;
    }
    @Override
    public boolean eliminarCliente(int id) {
        if(buscarUno(id) != null){
            cdao.deleteById(id);
            return true;
        }
        return false;
    }
    @Override
    public boolean modificarDatos(Cliente cliente) {

        if(buscarUno(cliente.getId())!=null){
            cdao.save(cliente);
            return true;
        }else return false;
    }
    @Override
    public Cliente buscarUno(int id) {
            return cdao.findById(id).orElse(null);
    }
}
