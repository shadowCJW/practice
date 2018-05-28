package cn.chenjw.codedemo.webserivce.jax;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

@Produces("application/json;charset=UTF-8")
public interface QueryService {
	@GET
	@Path(value="/queryget")
	String queryGet(@QueryParam("name")String name);
	
	@GET
	@Path(value="/queryget/{name}")
	String queryGet2(@PathParam("name")String name);
	
	@POST
	@Path(value="/querypost")
	String queryPost(String param);
	
	@POST
	@Path(value="/upload")
	@Consumes("multipart/form-data")
	String getFIleInfo(@Multipart(value="file")Attachment targetFile, @Multipart(value="param")String param);
}
