package com.edix.micro.microservicios.RESTController;

import com.edix.micro.microservicios.DAO.ClienteDao;
import com.edix.micro.microservicios.EntityBeans.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    final
    ClienteDao cdao;
    public ClienteController(ClienteDao cdao) {
        this.cdao = cdao;
    }
    @GetMapping("/todos")
    public List<Cliente> buscarTodos() { return cdao.buscarTodos();}

    @GetMapping("/uno/{id}")
    public Cliente buscarUnCliente(@PathVariable ("id") int idCliente){
        return cdao.buscarUno(idCliente);
    }
    @PostMapping("/alta")
    public Cliente altaCliente(@RequestBody Cliente cliente){
        return cdao.darAlta(cliente);
    }
    @DeleteMapping("/borrar/{id}")
    public String borrarCliente(@PathVariable("id") int id){
        if(cdao.eliminarCliente(id))
            return "Cliente eliminado";
        else return "Cliente no eliminado";
    }

    @PutMapping("/editar")
    public String modificarCliente(@RequestBody Cliente cliente){
        if(cdao.modificarDatos(cliente)) {
            return "Cliente modificado";
        } else return "Cliente no modificado";
    }
}
