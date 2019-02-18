
package ph.edu.celp.dev.studentrecordmanagement.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;


@Controller
@RequestMapping("/students/{studentId}")
class StudentEnrollmentController {

    private static final String VIEWS_ENROLLMENTS_CREATE_OR_UPDATE_FORM = "enrollments/createOrUpdateEnrollmentForm";
    private final StudentEnrollmentRepository seRepo;
    private final StudentRepository studentRepo;

    public StudentEnrollmentController(StudentEnrollmentRepository e, StudentRepository s) {
        this.seRepo = e;
        this.studentRepo = s;
    }

//    @ModelAttribute("types")
//    public Collection<EnrollmentType> populateEnrollmentTypes() {
//        return this.seRepo.findEnrollmentTypes();
//    }

    @ModelAttribute("student")
    public Student findStudent(@PathVariable("studentId") int studentId) {
        return this.studentRepo.findById(studentId);
    }

    @InitBinder("student")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

//    @InitBinder("enrollment")
//    public void initEnrollmentBinder(WebDataBinder dataBinder) {
//        dataBinder.setValidator(new EnrollmentValidator());
//    }

    @GetMapping("/enrollments/new")
    public String initCreationForm(Student student, ModelMap model) {
        StudentEnrollment se = new StudentEnrollment();
        student.addStudentEnrollment(se);
        model.put("se", se);
        return VIEWS_ENROLLMENTS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/enrollments/new")
    public String processCreationForm(Student owner, @Valid StudentEnrollment se, BindingResult result, ModelMap model) {
        if (StringUtils.hasLength(se.getSchoolTerm()) && se.isNew() && owner.getStudentEnrollment(se.getName(), true) != null){
            result.rejectValue("name", "duplicate", "already exists");
        }
        owner.addStudentEnrollment(se);
        if (result.hasErrors()) {
            model.put("se", se);
            return VIEWS_ENROLLMENTS_CREATE_OR_UPDATE_FORM;
        } else {
            this.seRepo.save(se);
            return "redirect:/students/{studentId}";
        }
    }

    @GetMapping("/enrollments/{enrollmentId}/edit")
    public String initUpdateForm(@PathVariable("enrollmentId") int enrollmentId, ModelMap model) {
        StudentEnrollment enrollment = this.seRepo.findById(enrollmentId);
        model.put("enrollment", enrollment);
        return VIEWS_ENROLLMENTS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/enrollments/{enrollmentId}/edit")
    public String processUpdateForm(@Valid StudentEnrollment studentEnrollment, BindingResult result, Student student, ModelMap model) {
        if (result.hasErrors()) {
        	studentEnrollment.setStudent(student);
            model.put("studentEnrollment", studentEnrollment);
            return VIEWS_ENROLLMENTS_CREATE_OR_UPDATE_FORM;
        } else {
        	student.addStudentEnrollment(studentEnrollment);
            this.seRepo.save(studentEnrollment);
            return "redirect:/students/{studentId}";
        }
    }

}
