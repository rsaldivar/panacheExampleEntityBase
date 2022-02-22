package org.acme.hibernate.orm.panache.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.security.auth.x500.X500PrivateCredential;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("entity/cliente")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class TDD_CLIENTEEntityResource {

    // Obtenemos una instancia de la clase EntityManager
    @Inject
    EntityManager entityManager;

    private static final Logger LOGGER = Logger.getLogger(TDD_CLIENTEEntityResource.class.getName());

    @GET
    public List<TDD_CLIENTE> get() {
        try {
            System.out.println("Consultar lista: " + TDD_CLIENTE.findAll());

        } catch (Exception e) {
            throw new WebApplicationException("Error al buscar el registro en TDD_CLIENTE " + e.getMessage());
        }
        return TDD_CLIENTE.listAll();
    }

    @GET
    @Path("{id}")
    public TDD_CLIENTE getSingle(@PathParam Long id) {
        TDD_CLIENTE entity = TDD_CLIENTE.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Fruit with id of " + id + " does not exist.", 404);
        }
        return entity;
    }

    @POST
    @Transactional
    public Response create(TDD_CLIENTE client) {
        if (client.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        System.out.println(client.toString());
        client.setEstatus(0);
        client.setIntento(0);
        client.setDato_cliente_numero(client.getDato_cliente_numero() == null ? "" : client.getDato_cliente_numero());
        client.setDato_cliente_seleccionado(client.getDato_cliente_seleccionado() == null ? "" : client.getDato_cliente_seleccionado());
        client.setEstatus_operacion(client.getEstatus_operacion() == null ? "" : client.getEstatus_operacion());
        client.setNuevo_plastico(client.getNuevo_plastico() == null ? "" : client.getNuevo_plastico());
        client.setFecha_creacion(new Date().toString());
        client.setFecha_modificacion(new Date().toString());
        client.persist();
        return Response.ok(client).status(201).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam Long id) {
        TDD_CLIENTE entity = TDD_CLIENTE.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Fruit with id of " + id + " does not exist.", 404);
        }
        entity.delete();
        return Response.status(204).build();
    }

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Inject
        ObjectMapper objectMapper;

        @Override
        public Response toResponse(Exception exception) {
            LOGGER.error("Failed to handle request", exception);

            int code = 500;
            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }

            ObjectNode exceptionJson = objectMapper.createObjectNode();
            exceptionJson.put("exceptionType", exception.getClass().getName());
            exceptionJson.put("code", code);

            if (exception.getMessage() != null) {
                exceptionJson.put("error", exception.getMessage());
            }

            return Response.status(code)
                    .entity(exceptionJson)
                    .build();
        }

    }
}
