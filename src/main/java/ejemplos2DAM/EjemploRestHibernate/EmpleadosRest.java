package ejemplos2DAM.EjemploRestHibernate;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.hibernate.*;

import ejemplos2DAM.Entidades.*;


/**
 * API para el tratamiento de empleados mediante ReST
 */
@Path("Empleados")
public class EmpleadosRest {
	private static SessionFactory sesFactory = HibernateUtil.getSessionFactory();
	
    @Context
    private UriInfo uriInfo;
    @Context
    private Request request;

	
    /**
     * Método para devolver los empleados en formato XML
     * @return String that will be returned as a text/plain response.
     */

    
    public static SessionFactory getSesFactory() {
		return sesFactory;
	}	
		
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Empleados> getEmpleados()
    {
    	Session session = sesFactory.openSession();
		
		List<Empleados> listaEmpleados = null;
		
		try {
			listaEmpleados = session.createQuery("FROM Empleados").list();
		} catch (HibernateException e) {

		} finally {
			session.close();
		}		
		
		return listaEmpleados;
    	
    }

	@GET
    @Path("/{empleadoId}")
    @Produces(MediaType.APPLICATION_XML)
	/**
	 * Devuelve un solo empleado especificado en la url
	 * @param empleadoId
	 * @return
	 */
    public Empleados getEmpleado(@PathParam("empleadoId") int empleadoId)
    {
    	Session session = sesFactory.openSession();

		Empleados empleado = null;
		
		try {
			empleado = session.get(Empleados.class, (short) empleadoId);
		} catch (HibernateException e) {

		} finally {
			session.close();
		}		
		
		return empleado;
    	
    }
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	/**
	 * Actualiza la BBDD con el contenido XML 
	 * 
	 * @param departamento
	 * @return
	 */
	public Response postEmpleado(Empleados empleado) {
		Session session = sesFactory.openSession();
		Response res;

	//	System.out.println(departamento.toString());
		try {
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(empleado); // Crear un nuevo registro o actulizar el existente
			tx.commit();
            res = Response.created(uriInfo.getAbsolutePath()).build();
		} catch (PersistenceException e) {
				e.printStackTrace();
			 res = Response.noContent().build();
		} finally {
			session.close();
		}		

		return res;
	}
}
