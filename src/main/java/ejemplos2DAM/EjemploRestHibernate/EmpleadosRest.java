package ejemplos2DAM.EjemploRestHibernate;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.*;

import ejemplos2DAM.Entidades.*;


/**
 * API para el tratamiento de empleados mediante ReST
 */
@Path("Empleados")
public class EmpleadosRest {
	private static SessionFactory sesFactory = HibernateUtil.getSessionFactory();
	
    /**
     * Método para devolver los empleados en formato XML
     * @return String that will be returned as a text/plain response.
     */

    
    public static SessionFactory getSesFactory() {
		return sesFactory;
	}	
	
	
    @GET
    @Path("/")
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
}
