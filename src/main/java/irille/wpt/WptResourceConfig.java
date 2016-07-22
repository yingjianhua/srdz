package irille.wpt;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;


@ApplicationPath("/wpt/*")
public class WptResourceConfig extends ResourceConfig{
	public WptResourceConfig() {
		System.out.println("-------------------wptResourceConfig--------");
		packages("irille.wpt");
	}
}
