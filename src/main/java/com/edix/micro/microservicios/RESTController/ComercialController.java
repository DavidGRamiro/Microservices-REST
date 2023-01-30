package com.edix.micro.microservicios.RESTController;

import com.edix.micro.microservicios.DAO.IntComercialDao;
import com.edix.micro.microservicios.EntityBeans.Comerciale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comercial")
public class ComercialController {
    final
    IntComercialDao cdao;

    public ComercialController(IntComercialDao cdao) {
        this.cdao = cdao;
    }

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

}
