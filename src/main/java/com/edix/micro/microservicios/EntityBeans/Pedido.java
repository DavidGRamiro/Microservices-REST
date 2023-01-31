package com.edix.micro.microservicios.EntityBeans;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "pedidos", schema = "ventasbbdd_2023", indexes = {
        @Index(name = "id_cliente", columnList = "id_cliente"),
        @Index(name = "id_comercial", columnList = "id_comercial")
})
public class Pedido implements Serializable {
    @Serial
    private static final long serialVersionUID = 7930724401080137208L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido", nullable = false)
    private Integer id;

    @Column(name = "total", nullable = false)
    private Double total;

    @Column(name = "fecha")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente idCliente;

    @ManyToOne
    @JoinColumn(name = "id_comercial", nullable = false)
    private Comerciale idComercial;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Comerciale getIdComercial() {
        return idComercial;
    }

    public void setIdComercial(Comerciale idComercial) {
        this.idComercial = idComercial;
    }

}