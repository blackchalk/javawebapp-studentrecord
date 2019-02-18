package ph.edu.celp.dev.studentrecordmanagement.student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;

import ph.edu.celp.dev.studentrecordmanagement.model.Person;


@Entity
@Table(name = "students")
public class Student extends Person {
    @Column(name = "middleName")
	private String middleName;
	
    public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "studentid")
    @NotEmpty
	private String studentid;
	
    @Column(name = "dob")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    
    @Column(name = "address")
    @NotEmpty
    private String address;
    
    @Column(name = "nationality")
    @NotEmpty
    private String nationality;
    
    @Column(name = "gender")
    @NotEmpty
    private String gender;
    
    @Column(name = "primaryschool")
    @NotEmpty
    private String primary;
    
    @Column(name = "intermediate")
    @NotEmpty
    private String intermediate;
    
    @Column(name = "highschool")
    @NotEmpty
    private String highschool;
    
    @Column(name = "date_of_admission")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfAdmission;
    
    @Column(name = "degree")
    @NotEmpty
	private String degree;
    
    @Column(name = "so_no")
    @NotEmpty
    private String soNumber;
    
    @Column(name = "schoolyear")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate sy;
    
    @Column(name = "date_of_graduation")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfGraduation;
    
    public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public Set<StudentEnrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(Set<StudentEnrollment> enrollments) {
		this.enrollments = enrollments;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private Set<StudentEnrollment> enrollments;


	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPrimary() {
		return primary;
	}

	public void setPrimary(String primary) {
		this.primary = primary;
	}

	public String getIntermediate() {
		return intermediate;
	}

	public void setIntermediate(String intermediate) {
		this.intermediate = intermediate;
	}

	public String getHighschool() {
		return highschool;
	}

	public void setHighschool(String highschool) {
		this.highschool = highschool;
	}

	public LocalDate getDateOfAdmission() {
		return dateOfAdmission;
	}

	public void setDateOfAdmission(LocalDate dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getSoNumber() {
		return soNumber;
	}

	public void setSoNumber(String soNumber) {
		this.soNumber = soNumber;
	}

	public LocalDate getSy() {
		return sy;
	}

	public void setSy(LocalDate sy) {
		this.sy = sy;
	}

	public LocalDate getDateOfGraduation() {
		return dateOfGraduation;
	}

	public void setDateOfGraduation(LocalDate dateOfGraduation) {
		this.dateOfGraduation = dateOfGraduation;
	}
	
	 protected Set<StudentEnrollment> getStudentEnrollmentsInternal() {
	        if (this.enrollments == null) {
	            this.enrollments = new HashSet<>();
	        }
	        return this.enrollments;
	    }

	    protected void setStudentEnrollmentsInternal(Set<StudentEnrollment> enrollments) {
	        this.enrollments = enrollments;
	    }

	    public List<StudentEnrollment> getStudentEnrollments() {
	        List<StudentEnrollment> sortedEnrollments = new ArrayList<>(getStudentEnrollmentsInternal());
	        PropertyComparator.sort(sortedEnrollments,
	                new MutableSortDefinition("starDate", true, true));
	        return Collections.unmodifiableList(sortedEnrollments);
	    }

	    public void addStudentEnrollment(StudentEnrollment studentEnrollment) {
	        if (studentEnrollment.isNew()) {
	        	getStudentEnrollmentsInternal().add(studentEnrollment);
	        }
	        studentEnrollment.setStudent(this);
	    }


	    public StudentEnrollment getStudentEnrollment(String name) {
	        return getStudentEnrollment(name, false);
	    }


	    public StudentEnrollment getStudentEnrollment(String name, boolean ignoreNew) {
	        name = name.toLowerCase();
	        for (StudentEnrollment studentEnrollment : getStudentEnrollmentsInternal()) {
	            if (!ignoreNew || !studentEnrollment.isNew()) {
	                String compName = studentEnrollment.getName();
	                compName = compName.toLowerCase();
	                if (compName.equals(name)) {
	                    return studentEnrollment;
	                }
	            }
	        }
	        return null;
	    }

	@Override
	public String toString() {
		return "Student [studentId=" + studentid + ", dob=" + dob + ", address=" + address + ", nationality="
				+ nationality + ", gender=" + gender + ", primary=" + primary + ", intermediate=" + intermediate
				+ ", highschool=" + highschool + ", dateOfAdmission=" + dateOfAdmission + ", degree=" + degree
				+ ", soNumber=" + soNumber + ", sy=" + sy + ", dateOfGraduation=" + dateOfGraduation + "]";
	}

}
