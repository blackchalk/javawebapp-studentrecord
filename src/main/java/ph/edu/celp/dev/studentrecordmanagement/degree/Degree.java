package ph.edu.celp.dev.studentrecordmanagement.degree;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import ph.edu.celp.dev.studentrecordmanagement.model.NamedEntity;
@Entity
@Table(name = "degrees")
public class Degree extends NamedEntity{

    @Column(name = "deptId")
    @NotEmpty
	private int deptId;
    
    @Column(name = "totalNumberOfCredits")
    @NotEmpty
	private int totalNumberOfCredits;

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public int getTotalNumberOfCredits() {
		return totalNumberOfCredits;
	}

	public void setTotalNumberOfCredits(int totalNumberOfCredits) {
		this.totalNumberOfCredits = totalNumberOfCredits;
	}

	@Override
	public String toString() {
		return "Degree [deptId=" + deptId + ", totalNumberOfCredits=" + totalNumberOfCredits + "]";
	}
    
    
	
}
