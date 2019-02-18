package ph.edu.celp.dev.studentrecordmanagement.grade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import ph.edu.celp.dev.studentrecordmanagement.model.BaseEntity;

@Entity
@Table(name = "studentGrades")
public class StudentGrade extends BaseEntity{

    @Column(name = "courseId")
	private int courseId;
    @Column(name = "finalGrade")
    private int finalGrade;
    @Column(name = "reexamGrade")
    private int reexamGrade;
    @Column(name = "enrollmentId")
    private int studentEnrollmentId;
    
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getFinalGrade() {
		return finalGrade;
	}
	public void setFinalGrade(int finalGrade) {
		this.finalGrade = finalGrade;
	}
	public int getReexamGrade() {
		return reexamGrade;
	}
	public void setReexamGrade(int reexamGrade) {
		this.reexamGrade = reexamGrade;
	}
	public int getStudentEnrollmentId() {
		return studentEnrollmentId;
	}
	public void setStudentEnrollmentId(int studentEnrollmentId) {
		this.studentEnrollmentId = studentEnrollmentId;
	}
    

    
    }
