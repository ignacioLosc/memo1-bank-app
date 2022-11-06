package com.aninfo.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cbu;

    private Double balance;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Descuento> descuentos;

    public Account(){
    }

    public Account(Double balance) {
        this.balance = balance;
    }

    public Long getCbu() {
        return cbu;
    }

    public void setCbu(Long cbu) {
        this.cbu = cbu;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
    public void depositar(Double monto) {
        for (Descuento descuento: descuentos) {
            this.balance = descuento.aplicarDescuento(monto);
        }
        this.balance += monto;
    }

    public void extraer(Double monto) {
        this.balance -= monto;
    }

    public void agregarDescuento(Descuento descuento) {
        descuentos.add(descuento);
    }
}
