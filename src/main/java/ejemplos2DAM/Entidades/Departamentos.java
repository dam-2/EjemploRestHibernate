package ejemplos2DAM.Entidades;
// Generated 31-ene-2019 10:48:31 by Hibernate Tools 5.2.11.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Departamentos generated by hbm2java
 */
@XmlRootElement
@Entity
@Table(name = "departamentos", catalog = "ejemplohibernate")
public class Departamentos implements java.io.Serializable {

	private byte deptNo;
	private String dnombre;
	private String loc;
	private Set<Empleados> empleadoses = new HashSet<Empleados>(0);

	public Departamentos() {
	}

	public Departamentos(byte deptNo) {
		this.deptNo = deptNo;
	}

	public Departamentos(byte deptNo, String dnombre, String loc, Set<Empleados> empleadoses) {
		this.deptNo = deptNo;
		this.dnombre = dnombre;
		this.loc = loc;
		this.empleadoses = empleadoses;
	}

	@Id

	@Column(name = "dept_no", unique = true, nullable = false)
	public byte getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(byte deptNo) {
		this.deptNo = deptNo;
	}

	@Column(name = "dnombre", length = 15)
	public String getDnombre() {
		return this.dnombre;
	}

	public void setDnombre(String dnombre) {
		this.dnombre = dnombre;
	}

	@Column(name = "loc", length = 15)
	public String getLoc() {
		return this.loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	@XmlTransient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "departamentos")
	public Set<Empleados> getEmpleadoses() {
		return this.empleadoses;
	}

	public void setEmpleadoses(Set<Empleados> empleadoses) {
		this.empleadoses = empleadoses;
	}

}