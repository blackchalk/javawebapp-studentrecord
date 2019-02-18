package ph.edu.celp.dev.studentrecordmanagement.grade;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;


public interface StudentGradeRepository extends Repository<StudentGrade, Integer>{
	
	
	void save (StudentGrade studentGrade) throws DataAccessException;
	List<StudentGrade> findByStudentEnrollmentId(Integer studentEnrollmentId);
}
