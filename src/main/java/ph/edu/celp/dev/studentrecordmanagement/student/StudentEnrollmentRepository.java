package ph.edu.celp.dev.studentrecordmanagement.student;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;



public interface StudentEnrollmentRepository extends Repository<StudentEnrollment, Integer>{

//	@Query("SELECT se.schoolTerm FROM StudentEnrollment se WHERE se.student_id =:id ORDER BY se.startDate")
//	String getTerm(String id);
	
	@Query("SELECT se FROM StudentEnrollment se WHERE se.id =:id")
    @Transactional(readOnly = true)
    StudentEnrollment findById(Integer id);
	
	void save(StudentEnrollment se);
}
