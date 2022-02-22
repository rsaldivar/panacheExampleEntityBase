package org.acme.hibernate.orm.panache.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.util.Date;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
public class TDD_MCA extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tdd_mca")
    @SequenceGenerator(name = "tdd_mca", sequenceName = "TDD_MCA_ID_SEQ", initialValue = 100)
    public Long id;

    @Column(name = "id_bitacora")
    private String id_bitacora;

    @Column(name = "id_cliente")
    private String id_cliente;

    @Column(name = "id_operacion")
    private String id_operacion;

    @Column(name = "peticion_xml")
    private String peticion_xml;

    @Column(name = "respuesta_xml")
    private String respuesta_xml;

    @Column(name = "peticion_json")
    private String peticion_json;
     
    @Column(name = "respuesta_json")
    private String respuesta_json;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "fecha_creacion")
    private String fecha_creacion;

    @Column(name = "fecha_modificacion")
    private String fecha_modificacion;

    @Column(name = "estatus")
    private Integer estatus;

    public String getPeticion_json() {
        return peticion_json;
    }

    public void setPeticion_json(String peticion_json) {
        this.peticion_json = peticion_json;
    }

    public String getRespuesta_json() {
        return respuesta_json;
    }

    public void setRespuesta_json(String respuesta_json) {
        this.respuesta_json = respuesta_json;
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

    public TDD_MCA(){

    }

    public TDD_MCA(String id_bitacora, String id_cliente, String id_operacion, String peticion_xml, String respuesta_xml, Date fecha) {
        this.id_bitacora = id_bitacora;
        this.id_cliente = id_cliente;
        this.id_operacion = id_operacion;
        this.peticion_xml = peticion_xml;
        this.respuesta_xml = respuesta_xml;
        this.fecha = new Date();
        this.fecha_creacion = this.fecha.toGMTString();
        this.fecha_modificacion = this.fecha.toGMTString();
    }

    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getId_bitacora() {
        return id_bitacora;
    }
    public void setId_bitacora(String id_bitacora) {
        this.id_bitacora = id_bitacora;
    }
    public String getId_cliente() {
        return id_cliente;
    }
    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }
    public String getId_operacion() {
        return id_operacion;
    }
    public void setId_operacion(String id_operacion) {
        this.id_operacion = id_operacion;
    }
    public String getPeticion_xml() {
        return peticion_xml;
    }
    public void setPeticion_xml(String peticion_xml) {
        this.peticion_xml = peticion_xml;
    }
    public String getRespuesta_xml() {
        return respuesta_xml;
    }
    public void setRespuesta_xml(String respuesta_xml) {
        this.respuesta_xml = respuesta_xml;
    }


}
