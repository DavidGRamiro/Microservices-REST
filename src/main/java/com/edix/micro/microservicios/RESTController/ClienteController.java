package com.edix.micro.microservicios.RESTController;

import com.edix.micro.microservicios.DAO.ClienteDao;
import com.edix.micro.microservicios.DTO.ClienteDTO;
import com.edix.micro.microservicios.EntityBeans.Cliente;
import com.edix.micro.microservicios.Modelo.DTO.InterfaceClienteDTOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteDao cdao;
    @Autowired
    InterfaceClienteDTOImpl cRepo;


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

    //Estas consultas van sobre una clase creada DTO para mostrar la informacion minima posible.
    //Controlando ademas la respuesta, dependiendo si nos trae un objeto o un string.
    //Tambien controlamos los estados HTTP

    @GetMapping("/simple/{id}")
    public ResponseEntity<?> informacionSimple(@PathVariable("id")  int idCliente){

        ClienteDTO cdto = cRepo.informacionSimple(idCliente);

        if(cdto == null)
            return new ResponseEntity<String>("Cliente no existe", HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.OK).body(cdto);
    }

}
