package info.getsocial.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import info.getsocial.domain.User;
import info.getsocial.domain.exception.ExceptionMessage;
import info.getsocial.domain.exception.GetSocialException;
import info.getsocial.security.SocialUserService;
import info.getsocial.service.UserService;

@Component
@Path("/user")
public class UserResource {
	
	@Autowired
	private SocialUserService userService;

//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path("/{id}")
//	public Response getUser(String id) {
//		try {
//			//User user = userService.getUser(id);
//			return Response.ok(null).build();
//		} catch (GetSocialException ex){
//			return Response.status(ex.getCode()).entity(ex.getMessage()).build();
//		}
//	}
//
//	@POST
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response createUser(@QueryParam("id") String id, @QueryParam("name") String name, 
//			@QueryParam("email") String email, @QueryParam("password") String password) {
//		try {
//			//userService.saveUser(id, name, email, password);
//			return Response.ok().build();
//		} catch(GetSocialException ex) {
//			return Response.status(ex.getCode()).entity(ex.getMessage()).build();
//		}
//	}
//	
//	@PUT
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response updateUser(@QueryParam("id") String id, @QueryParam("name") String name, 
//			@QueryParam("email") String email, @QueryParam("password") String password){
//		if(id == null) {
//			return Response.status(ExceptionMessage.INVALID_PARAMETERS.getCode())
//					.entity(ExceptionMessage.INVALID_PARAMETERS.getMessage()).build();
//		}
//		
//		try {
//			//User user = userService.getUser(id);
//			if(user == null){
//				return Response.status(ExceptionMessage.NOT_FOUND.getCode())
//						.entity(ExceptionMessage.NOT_FOUND.getMessage()).build();
//			}
//			//TODO: update
////			user.setEmail(email);
////			user.setName(name);
////			user.setPassword(password);
//			
//			//userService.updateUser(user);
//			return Response.ok().build();
//		} catch(GetSocialException ex) {
//			return Response.status(ex.getCode()).entity(ex.getMessage()).build();
//		}
//	}
//	
//	@DELETE
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response deleteUser(@QueryParam("id") String id) {
//		if(id == null) {
//			return Response.status(ExceptionMessage.INVALID_PARAMETERS.getCode())
//					.entity(ExceptionMessage.INVALID_PARAMETERS.getMessage()).build();
//		}
//		
//		try {
//			//User user = userService.getUser(id);
//			if(user == null){
//				return Response.status(ExceptionMessage.NOT_FOUND.getCode())
//						.entity(ExceptionMessage.NOT_FOUND.getMessage()).build();
//			}
//			//userService.deleteUser(user);
//			return Response.ok().build();
//		} catch(GetSocialException ex) {
//			return Response.status(ex.getCode()).entity(ex.getMessage()).build();
//		}
//	}
}
