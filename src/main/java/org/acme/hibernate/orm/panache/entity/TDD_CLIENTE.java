package org.acme.hibernate.orm.panache.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
public class TDD_CLIENTE extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tdd_client")
    @SequenceGenerator(name = "tdd_client", sequenceName = "TDD_CLIENTE_ID_SEQ", initialValue = 100)
    public Long id;

    @Column(name = "identificador_cliente")
    private String identificador_cliente;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "dato_cliente_seleccionado")
    private String dato_cliente_seleccionado;

    @Column(name = "dato_cliente_numero")
    private String dato_cliente_numero;

    @Column(name = "estatus_operacion")
    private String estatus_operacion;
     
    @Column(name = "nuevo_plastico")
    private String nuevo_plastico;

    @Column(name = "fecha_creacion")
    private String fecha_creacion;

    @Column(name = "fecha_modificacion")
    private String fecha_modificacion;

    @Column(name = "estatus")
    private Integer estatus;


    public TDD_CLIENTE(){
        this.dato_cliente_numero = "NumCliente";
        this.dato_cliente_seleccionado = "Seleccionado";
        this.estatus_operacion = "00";
        this.estatus          = 0;
        this.fecha_creacion   = "01012021";
        this.fecha_modificacion = "01012021";
        this.nombre = "Nombre";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificador_cliente() {
        return identificador_cliente;
    }

    public void setIdentificador_cliente(String identificador_cliente) {
        this.identificador_cliente = identificador_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDato_cliente_seleccionado() {
        return dato_cliente_seleccionado;
    }

    public void setDato_cliente_seleccionado(String dato_cliente_seleccionado) {
        this.dato_cliente_seleccionado = dato_cliente_seleccionado;
    }

    public String getDato_cliente_numero() {
        return dato_cliente_numero;
    }

    public void setDato_cliente_numero(String dato_cliente_numero) {
        this.dato_cliente_numero = dato_cliente_numero;
    }

    public String getEstatus_operacion() {
        return estatus_operacion;
    }

    public void setEstatus_operacion(String estatus_operacion) {
        this.estatus_operacion = estatus_operacion;
    }

    public String getNuevo_plastico() {
        return nuevo_plastico;
    }

    public void setNuevo_plastico(String nuevo_plastico) {
        this.nuevo_plastico = nuevo_plastico;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(String fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public Integer getIntento() {
        return intento;
    }

    public void setIntento(Integer intento) {
        this.intento = intento;
    }

    @Column(name = "intento")
    private Integer intento;


}
