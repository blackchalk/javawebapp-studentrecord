package ph.edu.celp.dev.studentrecordmanagement.course;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import ph.edu.celp.dev.studentrecordmanagement.model.BaseEntity;
@Entity
@Table(name = "courses")
public class Course extends BaseEntity{
	
    @Column(name = "title")
	private String title;
    @Column(name = "creditScore")
	private int creditScore;
    @Column(name = "deptId")
	private int deptId;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
    
}
