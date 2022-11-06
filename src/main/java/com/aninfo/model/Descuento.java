package com.aninfo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Descuento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double porcentaje;

    public Descuento(){
    }

    public Descuento(Double porcentaje) {
        this.porcentaje = porcentaje/100;
    }

    public Double aplicarDescuento(Double monto) {
        if (monto > 5000) {
            return Double.valueOf(500);
        } else {
            return Double.valueOf(monto*porcentaje);
        }
    }
}
