package it.corso.controller;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import it.corso.dto.UtenteDto;
import it.corso.dto.UtenteLoginRequestDto;
import it.corso.dto.UtenteLoginResponseDto;
import it.corso.dto.UtenteSignupDto;
import it.corso.dto.UtenteUpdateDto;
import it.corso.jwt.JWTTokenNeeded;
import it.corso.jwt.Secured;
import it.corso.model.Ruolo;
import it.corso.model.Utente;
import it.corso.service.UtenteService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

//@JWTTokenNeeded
@Secured(role = "Admin")
@Path("/utente")
public class UtenteController {
	
	@Autowired
	private UtenteService utenteService;
	
	public UtenteLoginResponseDto issueToken(String email) {
		byte [] secret = "4ndjnjcfmadkcaflkamdakfòldfckmòakjdkxjdkcjc99999".getBytes();
		Key key = Keys.hmacShaKeyFor(secret);
		
		Utente informazioniUtente = utenteService.getUserByEmail(email);
		
		Map<String, Object> map = new HashMap<>();
		map.put("nome", informazioniUtente.getFirstname());
		map.put("cognome", informazioniUtente.getLastname());
		map.put("email", email);
		
		List<String> ruoli = new ArrayList<>();
		
		for(Ruolo ruolo :informazioniUtente.getListaRuoli())
		{
			ruoli.add(ruolo.getTipologia().name());
		}
		
		map.put("ruoli", ruoli);
		
		Date creation = new Date();
		Date end = java.sql.Timestamp.valueOf(LocalDateTime.now().plusMinutes(15L));
		
		String tokenJwts = Jwts.builder()
				.setClaims(map)
				.setIssuer("http:/localhost:8080")
				.setIssuedAt(creation)
				.setExpiration(end)
				.signWith(key)
				.compact();
		
		UtenteLoginResponseDto token = new UtenteLoginResponseDto();
		
		token.setToken(tokenJwts);
		token.setTokenCreationTime(creation);
		token.setTtl(end);
		
		return token;
	}
	
	@POST
	@Path("/reg")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response userSignup(@RequestBody UtenteSignupDto utente) {
		try {
			if(!Pattern.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,20}", utente.getPassword())) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
			
			if (utenteService.existsUtenteByEmail(utente.getEmail())) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
			utenteService.userSignup(utente);
			return Response.status(Response.Status.OK).build();
		}
		catch (Exception ex) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Path("/get/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserByEmail(@PathParam("email") String email) {
		try {
			Utente user = utenteService.getUserByEmail(email);
			return Response.status(Response.Status.OK).entity(user).build();
		}
		catch (Exception ex) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Path("/get/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers() {
		 try {
			 List<UtenteDto> list = utenteService.getAllUser();
		      
		     return Response.status(Response.Status.OK).entity(list).build();
		 }
		 catch (Exception ex) {
			 return Response.status(Response.Status.BAD_REQUEST).build();
		 }
	}
	
	
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(@RequestBody UtenteUpdateDto utenteUpdateDto) {
	    
		try {

	      utenteService.userUpdate(utenteUpdateDto);

	      List<UtenteDto> list = utenteService.getAllUser();
	      
	      return Response.status(Response.Status.OK).entity(list).build();
	      
	    } catch(Exception e) {
	      return Response.status(Response.Status.BAD_REQUEST).build();
	    }
	}
	
	
	@DELETE
	@Path("/delete/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("email") String email) {
		try {
			utenteService.userDelete(email);
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		catch(Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(@RequestBody UtenteLoginRequestDto utenteLoginRequestDto) {
		try {
			if (utenteService.login(utenteLoginRequestDto)) {
				System.out.println("Credenziali valide");
				return Response.ok(issueToken(utenteLoginRequestDto.getEmail())).build();
			}
			
		} catch (Exception e) {
			return Response.status (Response.Status.BAD_REQUEST).build();
		}
		
		return Response.status (Response.Status.BAD_REQUEST).build();
	}
	
	@PUT
	@Path("{id_user}/corso/{id_corso}/subscribe")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCourse(@PathParam("id_user") int user_id, @PathParam("id_corso") int course_id) {
		try {
			utenteService.addCourse(user_id, course_id);
			return Response.status(Response.Status.OK).build();
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
		}
	}
}
