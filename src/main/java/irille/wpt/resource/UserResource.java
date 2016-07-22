package irille.wpt.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import irille.wx.wx.WxUser;

@Path("user")
public class UserResource {

	public UserResource() {
		System.out.println("--------------userResource");
	}
	@Path("/{userId:[0-9]*}")
	@GET
	@Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	public WxUser getUserByPath(@PathParam("userId") final Long userId) {
		return WxUser.load(WxUser.class, userId);
	}
	@POST
	public void aaaaa() {
		System.out.println("aaaaaaaaaaaa");
	}
}
