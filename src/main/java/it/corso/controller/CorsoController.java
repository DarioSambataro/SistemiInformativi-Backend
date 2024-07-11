package it.corso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import it.corso.dto.CorsoDto;
import it.corso.dto.CorsoInsertDto;

import it.corso.jwt.Secured;
import it.corso.model.Corso;
import it.corso.model.Utente;
import it.corso.service.CorsoService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Secured(role = "Admin")
@Path("/corso")
public class CorsoController {
	
	@Autowired
	public CorsoService corsoService;
	
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertCorso(@RequestBody CorsoInsertDto corso) {
		try {
			corsoService.insertCorso(corso);
			return Response.status(Response.Status.OK).build();
		}
		catch(Exception ex) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCorso(@PathParam("id") int id) {
		try {
			corsoService.corsoDelete(id);
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		catch(Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCorsorById(@PathParam("id") int id) {
		try {
			CorsoDto corso = corsoService.getCorsoById(id);
			return Response.status(Response.Status.OK).entity(corso).build();
		}
		catch (Exception ex) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Path("/get/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCorsi() {
		try {
			List<CorsoDto> corsi = corsoService.getCorsi();
			return Response.status(Response.Status.OK).entity(corsi).build();
		}catch (Exception ex) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	 @DELETE
	 @Path("/corso/delete/{id}")
	 public void deleteCorsoByCategory(@PathParam("id") int idCa) {
	     corsoService.deleteCorsoByCategory(idCa);
	}
}
