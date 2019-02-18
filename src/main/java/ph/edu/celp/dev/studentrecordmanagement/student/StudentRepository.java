package ph.edu.celp.dev.studentrecordmanagement.student;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface StudentRepository extends Repository<Student, Integer> {

	@Query("SELECT DISTINCT student FROM Student student WHERE student.lastName LIKE :lastName%")
    @Transactional(readOnly = true)
	Collection<Student> findByLastName(@Param("lastName") String lastName);
	
	@Query("SELECT DISTINCT student FROM Student student WHERE student.lastName LIKE :lastName% OR student.studentid LIKE :lastName%")
    @Transactional(readOnly = true)
	Collection<Student> findByLastNameAndId(@Param("lastName") String lastName);
	
	
	@Query("SELECT student FROM Student student WHERE student.studentid =:id")
    @Transactional(readOnly = true)
	Student findById(@Param("studentid") String id);
	
	void save(Student student);
	
}
