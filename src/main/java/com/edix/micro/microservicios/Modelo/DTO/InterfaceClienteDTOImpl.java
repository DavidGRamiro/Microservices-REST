package com.edix.micro.microservicios.Modelo.DTO;

import com.edix.micro.microservicios.DTO.ClienteDTO;
import com.edix.micro.microservicios.EntityBeans.Cliente;
import com.edix.micro.microservicios.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterfaceClienteDTOImpl implements InterfaceClienteDTO {

    private final ClienteRepository cRepo;

    public InterfaceClienteDTOImpl(ClienteRepository cRepo) {
        this.cRepo = cRepo;
    }

    @Override
    public ClienteDTO informacionSimple(int idCliente) {
        Cliente cliente = cRepo.findById(idCliente).orElse(null);
        if(cliente == null)
            return null;

        ClienteDTO cdto = new ClienteDTO();
        cdto.setId(cliente.getId());
        cdto.setNombre(cliente.getNombre());
        return cdto;
    }
}
