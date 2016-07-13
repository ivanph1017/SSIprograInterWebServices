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

import services.beans.Provincia;
import services.dao.Conexion;
import services.dao.ProvinciaDAO;

@Path("/provincias")
public class ProvinciasService {
	@GET
	@Consumes("text/plain; charset=utf-8")
	@Produces("application/json; charset=utf-8")
	public Provincia verProvincia(
			@QueryParam("id") int id){
			try {
				return new ProvinciaDAO(Conexion.getInstancia().getEntityManager()).obtenerProvincia(id);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}
	
	
	@Path("/listaFiltro")
	@GET
	@Produces("application/json; charset=utf-8")
	public List<Provincia> listaProvinciasFiltro(
			@QueryParam("idDepartamento") int idDepartamento){	
		 try {
			 return new ProvinciaDAO(Conexion.getInstancia().getEntityManager()).listaFiltro(idDepartamento);			 
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@Path("/lista")
	@GET
	@Produces("application/json; charset=utf-8")
	public List<Provincia> listaProvincias(){	
		 try {
			 return new ProvinciaDAO(Conexion.getInstancia().getEntityManager()).lista();		 
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@POST
	@Consumes("application/json; charset=utf-8")
	@Produces("text/plain; charset=utf-8")
	public String agregarProvincia(Provincia provincia){
		try {
			new ProvinciaDAO(Conexion.getInstancia().getEntityManager()).registrar(provincia);
			return "Provincia registrada satisfactoriamente";
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
	public String cargarProvincias(String sql){
		try {
			new ProvinciaDAO(Conexion.getInstancia().getEntityManager()).cargaMasiva(sql);
			return "Provincias cargadas satisfactoriamente";
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Hubo un error en la carga";
		}
	}
	@PUT
	@Consumes("application/json; charset=utf-8")
	@Produces("text/plain; charset=utf-8")
	public String modificarProvincia(Provincia provincia){
		try {
			new ProvinciaDAO(Conexion.getInstancia().getEntityManager()).editar(provincia);
			return "Provincia modificada satisfactoriamente";
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Hubo un error en la modificación";
		}
	}
	@DELETE
	@Consumes("text/plain; charset=utf-8")
	@Produces("text/plain; charset=utf-8")
	public String eliminarProvincia(
			@QueryParam("id") int id){
		try {
			new ProvinciaDAO(Conexion.getInstancia().getEntityManager()).eliminar(id);
			return "Provincia eliminada satisfactoriamente";
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Hubo un error en la eliminación";
		}
	}
}
