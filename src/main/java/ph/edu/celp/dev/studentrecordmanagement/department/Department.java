package ph.edu.celp.dev.studentrecordmanagement.department;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import ph.edu.celp.dev.studentrecordmanagement.model.NamedEntity;
@Entity
@Table(name = "departments")
public class Department extends NamedEntity {
	
    @Column(name = "deptTitle")
	private String deptTitle;
    @Column(name = "deptDescription")
	private String deptDescription;
    @Column(name = "deptHead")
	private String deptHead;
    @Column(name = "deptFounder")
	private String deptFounder;
    
	public String getDeptTitle() {
		return deptTitle;
	}
	public void setDeptTitle(String deptTitle) {
		this.deptTitle = deptTitle;
	}
	public String getDeptDescription() {
		return deptDescription;
	}
	public void setDeptDescription(String deptDescription) {
		this.deptDescription = deptDescription;
	}
	public String getDeptHead() {
		return deptHead;
	}
	public void setDeptHead(String deptHead) {
		this.deptHead = deptHead;
	}
	public String getDeptFounder() {
		return deptFounder;
	}
	public void setDeptFounder(String deptFounder) {
		this.deptFounder = deptFounder;
	}

}
