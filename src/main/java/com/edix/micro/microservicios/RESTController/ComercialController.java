package com.edix.micro.microservicios.RESTController;

import com.edix.micro.microservicios.DAO.ClienteDao;
import com.edix.micro.microservicios.DAO.ComercialDao;
import com.edix.micro.microservicios.DAO.IntComercialDao;
import com.edix.micro.microservicios.EntityBeans.Cliente;
import com.edix.micro.microservicios.EntityBeans.Comerciale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comercial")
public class ComercialController {

    @Autowired
    ComercialDao cdao;

    @Autowired
    ClienteDao cli;
    @GetMapping("/todos")
    public List<Comerciale> buscarTodosComerciales() {
        return cdao.buscarTodos();
    }

    @PostMapping("/alta")
    public Comerciale darALta(@RequestBody Comerciale comercial){
      return cdao.insertar(comercial);
    }

    @GetMapping("/uno/{id}")
    public Optional<Comerciale> buscarComercialPorId(@PathVariable("id") int id) {
        return cdao.buscarPorId(id);
    }

    @PutMapping("/editar")
    public Comerciale actulizarDatos(@RequestBody Comerciale comercial){
        return cdao.actualizar(comercial);
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarComercial(@PathVariable("id") int id){
        if(cdao.eliminar(id))
            return "Comercial eliminado";
        else
            return "No se pudo eliminar el comercial";
    }
    @GetMapping("/bycliente/{id}")
    public ResponseEntity<?> buscarPorCliente(@PathVariable("id") int id){

       List<Comerciale> lista = cdao.buscarPorIdCliente(id);

       if(!lista.isEmpty())
           return ResponseEntity.status(HttpStatus.OK).body(lista);
       return new ResponseEntity<String>("El cliente seleccionado no tiene pedidos asignados a comercial", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/conpedidos")
    public ResponseEntity<?> comercialesConPedidos(){
        List<Comerciale> lista = cdao.comercialConPedido();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

}
