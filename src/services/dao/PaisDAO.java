package services.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;

import services.beans.Departamento;
import services.beans.Distrito;
import services.beans.Pais;
import services.beans.Provincia;

public class PaisDAO {
	private EntityManager em;
	public PaisDAO(EntityManager em) {
		super();
		this.em = em;
	}

	public void registrar(Pais pais) throws ServletException{
		
		em.getTransaction().begin();
		em.persist(pais);
		em.getTransaction().commit();
	}
	
	public void editar(Pais pais) throws ServletException{
		em.getTransaction().begin();
		em.merge(pais);
		em.getTransaction().commit();
	}
	
	public void eliminar(int id) throws ServletException{
		em.getTransaction().begin();
		DepartamentoDAO departamentoDAO=new DepartamentoDAO(em);
		ProvinciaDAO provinciaDAO=new ProvinciaDAO(em);
		DistritoDAO distritoDAO=new DistritoDAO(em);
		List<Departamento> listaDepartamentos=departamentoDAO.listaFiltro(id);
		List<Provincia> listaProvincias;
		List<Distrito> listaDistritos;
		
		for(Departamento departamento : listaDepartamentos){
			listaProvincias=provinciaDAO.listaFiltro(departamento.getId());
			for(Provincia provincia : listaProvincias){
				listaDistritos=distritoDAO.listaFiltro(provincia.getId());
				for(Distrito distrito : listaDistritos){
					distritoDAO.eliminar(distrito.getId());
				}
				provinciaDAO.eliminar(provincia.getId());
			}
			departamentoDAO.eliminar(departamento.getId());
		}
		
		Pais pais=obtenerPais(id);
		em.remove(pais);
		em.getTransaction().commit();
	}
	
	public Pais obtenerPais(int id) throws ServletException{
		Query query=em.createQuery("SELECT p FROM Pais p where p.id=:id");
		query.setParameter("id", id);
		Pais pais=(Pais)query.getSingleResult();
		return pais;
	}
	
	public List<Pais> lista() throws ServletException{
		Query query=em.createQuery("SELECT p FROM Pais p");				
		List<Pais> lista=(List<Pais>)query.getResultList();
		return lista;
	}	
	
}
