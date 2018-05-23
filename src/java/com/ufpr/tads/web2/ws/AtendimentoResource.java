package com.ufpr.tads.web2.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.ufpr.tads.web2.beans.Atendimento;
import com.ufpr.tads.web2.facade.AtendimentoFacade;

@Path("atendimentos")
public class AtendimentoResource{
    @Context
    private UriInfo context;
       
    /**
     * @see Application#Application()
     */
    public AtendimentoResource() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Retrieves representation of an instance of AtendimentoResource
     * @return an instance of String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAtendimentos() {
    	//Retorna uma lista com todos os atendimentos
    	try {
    		List<Atendimento> atendimentos = AtendimentoFacade.searchAll();
    		GenericEntity<List<Atendimento>> lista = new GenericEntity<List<Atendimento>>(atendimentos) {};
    		if(lista.getEntity() == null) {
    			return Response
    					.status(Response.Status.NO_CONTENT)
    					.build();
    		}else { 
    			return Response
    					.ok()
    					.entity(lista)
    					.build();
    		}
		} catch (Exception e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.build();
		}
    }

    @GET
    @Path("/form")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getForm() {
    	//Retorna um formulário de atendimento
    	try {
    		GenericEntity<ArrayList<List<?>>> form = new GenericEntity<ArrayList<List<?>>>(AtendimentoFacade.form()) {};
    		if(form.getEntity() == null) {
    			return Response
    					.status(Response.Status.NO_CONTENT)
    					.build();
    		}else { 
    			return Response
    					.ok()
    					.entity(form)
    					.build();
    		}
		} catch (Exception e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.build();
		}
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAtendimento(@PathParam("id") int idAtendimento) {
    	//Retorna o atendimento de código (id)
    	try {
    		Atendimento atendimento = AtendimentoFacade.search(idAtendimento);
    		if(atendimento == null) {
    			return Response
    					.status(Response.Status.NOT_FOUND)
    					.build();    			
    		}else {
    			return Response
    					.ok()
    					.entity(atendimento)
    					.build();		    			
    		}
		} catch (Exception e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.build();
		}
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postAtendimento(Atendimento atendimento) {
    	//Insere um novo atendimento
		try {
			AtendimentoFacade.insert(atendimento);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
    
    /**
     * PUT method for updating or creating an instance of AtendimentoResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Path("/resolve/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putAtendimento(@PathParam("id") int idAtendimento) {
    	//Resolve o atendimento de código (idAtendimento)
    	try {
    		AtendimentoFacade.solve(idAtendimento);
    		return Response.ok().build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
    }

}