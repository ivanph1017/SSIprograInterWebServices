package services.rest;


import java.util.List;

import javax.servlet.ServletException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import services.beans.Pais;
import services.dao.Conexion;
import services.dao.PaisDAO;


@Path("/paises")
public class PaisesService {
	@GET
	@Consumes("text/plain; charset=utf-8")
	@Produces("application/json; charset=utf-8")
	public Pais verPais(
			@QueryParam("id") int id){
			try {
				return new PaisDAO(Conexion.getInstancia().getEntityManager()).obtenerPais(id);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}
	
	
	@Path("/lista")
	@GET
	@Produces("application/json; charset=utf-8")
	public List<Pais> listaPaises(){	
		 try {
			 return new PaisDAO(Conexion.getInstancia().getEntityManager()).lista();			 
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@POST
	@Consumes("application/json; charset=utf-8")
	@Produces("text/plain; charset=utf-8")
	public String agregarPais(Pais pais){
		try {
			new PaisDAO(Conexion.getInstancia().getEntityManager()).registrar(pais);
			return "Pais registrado satisfactoriamente";
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Hubo un error en el registro";
		}
	}
	@Path("/carga")
	@POST
	@Consumes("text/plain; charset=utf-8")
	@Produces("text/plain; charset=utf-8")
	public String cargarPaises(String sql){
		try {
			new PaisDAO(Conexion.getInstancia().getEntityManager()).cargaMasiva(sql);
			return "Paises cargados satisfactoriamente";
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Hubo un error en la carga";
		}
	}
	@PUT
	@Consumes("application/json; charset=utf-8")
	@Produces("text/plain; charset=utf-8")
	public String modificarPais(Pais pais){
		try {
			new PaisDAO(Conexion.getInstancia().getEntityManager()).editar(pais);
			return "Pais modificado satisfactoriamente";
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Hubo un error en la modificación";
		}
	}
	@DELETE
	@Consumes("text/plain; charset=utf-8")
	@Produces("text/plain; charset=utf-8")
	public String eliminarPais(
			@QueryParam("id") int id){
		try {
			new PaisDAO(Conexion.getInstancia().getEntityManager()).eliminar(id);
			return "Pais eliminado satisfactoriamente";
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Hubo un error en la eliminación";
		}
	}
}
