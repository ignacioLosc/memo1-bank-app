package com.aninfo.model;

import javax.persistence.*;

@Entity
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double monto;

    private Long cbu;

    private String tipo;

    public Transaccion() {
    }

    public Transaccion(Double monto) {
        this.monto = monto;
    }

    public Double getMonto() {
        return monto;
    }

    public void setCbu(Long cbu) {
        this.cbu = cbu;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
