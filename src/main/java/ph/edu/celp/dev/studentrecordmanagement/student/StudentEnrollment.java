package ph.edu.celp.dev.studentrecordmanagement.student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import ph.edu.celp.dev.studentrecordmanagement.grade.StudentGrade;
import ph.edu.celp.dev.studentrecordmanagement.model.NamedEntity;



@Entity
@Table(name = "studentEnrollments")
public class StudentEnrollment extends NamedEntity{

	@Column(name = "schoolTerm")
	private String schoolTerm;
	@Column(name = "startDate")
	private LocalDate startDate;
	@Column(name = "endDate")
	private LocalDate endDate;
	
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
   
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentEnrollmentId", fetch = FetchType.EAGER)
    private Set<StudentGrade> studentGrades = new LinkedHashSet<>();


	public String getSchoolTerm() {
		return schoolTerm;
	}


	public void setSchoolTerm(String schoolTerm) {
		this.schoolTerm = schoolTerm;
	}


	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}


	public LocalDate getEndDate() {
		return endDate;
	}


	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	public Set<StudentGrade> getStudentGrades() {
		return studentGrades;
	}


	public void setStudentGrades(Set<StudentGrade> studentGrades) {
		this.studentGrades = studentGrades;
	}
	
    protected Set<StudentGrade> getGradesInternal() {
        if (this.studentGrades == null) {
            this.studentGrades = new HashSet<>();
        }
        return this.studentGrades;
    }
	
    public List<StudentGrade> getGrades() {
        List<StudentGrade> sortedGrades = new ArrayList<>(getGradesInternal());
        PropertyComparator.sort(sortedGrades,
                new MutableSortDefinition("courseId", false, false));
        return Collections.unmodifiableList(sortedGrades);
    }

    public void addGrade(StudentGrade grade) {
    	getGradesInternal().add(grade);
    	grade.setStudentEnrollmentId(this.getId());
    }
    
    
}
