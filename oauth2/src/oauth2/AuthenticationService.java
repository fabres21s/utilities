package oauth2;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;

@Path("/auth")
public class AuthenticationService {

	
	@POST
	@Path("/verify")	
	@Consumes(MediaType.APPLICATION_JSON)
	public void registrar(String json) throws JSONException, URISyntaxException {
		
		System.out.println("redirigiendo ::: recibiendo "+json);
		URI uri  = new URI("admin.html");
		Response.temporaryRedirect(uri);
	}
	
	@GET
	@Path("/get")	
	@Consumes(MediaType.APPLICATION_JSON)
	public void registrar() throws JSONException, URISyntaxException {
		
		System.out.println("redirigiendo ::: recibiendo :: get");
		URI uri  = new URI("admin.html");
	}
}

