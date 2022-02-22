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

@Path("entity/mca")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class TDD_MCAEntityResource {

    // Obtenemos una instancia de la clase EntityManager
    @Inject
    EntityManager entityManager;

    private static final Logger LOGGER = Logger.getLogger(TDD_MCAEntityResource.class.getName());

    @GET
    public List<TDD_MCA> get() {
        try {
            System.out.println("Consultar lista: " + TDD_MCA.findAll());
            List<TDD_MCA> lista = new ArrayList<>();
            //lista = entityManager.createQuery("SELECT T FROM TDD_MCA T", TDD_MCA.class).getResultList();
            //System.out.println("Consultar lista EntityManager: " + lista);
            //System.out.println("Consultar element EntityManager: " + lista);

        } catch (Exception e) {
            throw new WebApplicationException("Error al buscar el registro en TDD_MCA " + e.getMessage());
        }
        return TDD_MCA.listAll();
    }

    @GET
    @Path("{id}")
    public TDD_MCA getSingle(@PathParam Long id) {
        TDD_MCA entity = TDD_MCA.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Fruit with id of " + id + " does not exist.", 404);
        }
        return entity;
    }

    @POST
    @Transactional
    public Response create(TDD_MCA mca) {
        if (mca.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        System.out.println(mca.toString());
        mca.setEstatus(0);
        mca.setFecha(new Date());
        mca.setFecha_creacion(mca.getFecha().toString());
        mca.setFecha_modificacion(mca.getFecha().toString());
        mca.setPeticion_json(mca.getPeticion_xml());
        mca.setRespuesta_json(mca.getRespuesta_xml());
        mca.persist();
        return Response.ok(mca).status(201).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam Long id) {
        TDD_MCA entity = TDD_MCA.findById(id);
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
