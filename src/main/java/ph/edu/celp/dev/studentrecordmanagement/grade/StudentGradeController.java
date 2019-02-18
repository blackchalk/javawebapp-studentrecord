
package ph.edu.celp.dev.studentrecordmanagement.grade;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import ph.edu.celp.dev.studentrecordmanagement.student.StudentEnrollment;
import ph.edu.celp.dev.studentrecordmanagement.student.StudentEnrollmentRepository;


import javax.validation.Valid;
import java.util.Map;


@Controller
class StudentGradeController {

    private final StudentGradeRepository gradeRepository;
    private final StudentEnrollmentRepository enrollmentRepository;


    public StudentGradeController(StudentGradeRepository g, StudentEnrollmentRepository e) {
        this.gradeRepository = g;
        this.enrollmentRepository = e;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("grade")
    public StudentGrade loadEnrollmentWithGrade(@PathVariable("enrollmentId") int enrollmentId, Map<String, Object> model) {
        StudentEnrollment se = this.enrollmentRepository.findById(enrollmentId);
        model.put("se", se);
        StudentGrade grade = new StudentGrade();
        se.addGrade(grade);
        return grade;
    }

    // Spring MVC calls method 
    @GetMapping("/students/*/enrollments/{enrollmentId}/grades/new")
    public String initNewGradeForm(@PathVariable("enrollmentId") int enrollmentId, Map<String, Object> model) {
        return "enrollments/createOrUpdateGradeForm";
    }

    // Spring MVC calls method 
    @PostMapping("/students/{id}/enrollments/{endrollmentId}/grades/new")
    public String processNewGradeForm(@Valid StudentGrade grade, BindingResult result) {
        if (result.hasErrors()) {
            return "enrollments/createOrUpdateGradeForm";
        } else {
            this.gradeRepository.save(grade);
            return "redirect:/enrollments/{enrollmentId}";
        }
    }

}
