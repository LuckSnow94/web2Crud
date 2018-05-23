package com.ufpr.tads.web2.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import com.ufpr.tads.web2.beans.Produto;
import com.ufpr.tads.web2.facade.ProdutoFacade;

@Path("/produtos")
public class ProdutoResource extends Application {
    @Context
    private UriInfo context;
       
    /**
     * @see Application#Application()
     */
    public ProdutoResource() {
        super();
    }

    /**
     * Retrieves representation of an instance of ProdutoResource
     * @return an instance of String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProdutos() {
        //Retorna uma lista com todos os produtos
    	try {
    		List<Produto> produtos = ProdutoFacade.searchAll();
    		GenericEntity<List<Produto>> lista = new GenericEntity<List<Produto>>(produtos) {};
    		if(lista.getEntity() == null)
    			return Response
    					.status(Response.Status.NO_CONTENT)
    					.build();
    		else 
    			return Response
    					.ok()
    					.entity(lista)
    					.build();    		
    	}catch (Exception e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.build();
		}
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduto(@PathParam("id") int id) {
    	//Retorna o produto de código (id)
    	try {
    		Produto produto = ProdutoFacade.search(id);
    		if(produto == null) 
    			return Response
    					.status(Response.Status.NOT_FOUND)
    					.build();
    		else 
    			return Response
    					.ok()
    					.entity(produto)
    					.build();    		
    	}catch (Exception e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.build();
		}
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postProduto(String nomeProduto) {
    	//Insere um novo produto
    	try {
    		ProdutoFacade.insert(nomeProduto);
    		return Response
    				.ok()
    				.build();
    	}catch (Exception e) {
    		return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.build();
		}
    }
    
    /**
     * PUT method for updating or creating an instance of ProdutoResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putProduto(@PathParam("id") int idProduto, String nomeProduto) {
    	//Atualiza o produto de código (idProduto)
    	try {
    		ProdutoFacade.update(new Produto(idProduto,nomeProduto));
    		return Response
    				.ok()
    				.build();
		} catch (Exception e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.build();
		}
    }
    
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteProduto(@PathParam("id") int id) {
    	//Remove o produto de código (id)
    	try {
    		ProdutoFacade.delete(id);
    		return Response
    				.ok()
    				.build();    		
		} catch (Exception e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.build();
		}
    }

}