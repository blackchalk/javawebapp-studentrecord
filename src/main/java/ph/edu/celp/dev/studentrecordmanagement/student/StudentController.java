package ph.edu.celp.dev.studentrecordmanagement.student;


import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class StudentController {
	
	 private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "students/createOrUpdateStudentForm";
	    private final StudentRepository studentsRepo;


	    public StudentController(StudentRepository studentRepoService) {
	        this.studentsRepo = studentRepoService;
	    }

	    @InitBinder
	    public void setAllowedFields(WebDataBinder dataBinder) {
	        dataBinder.setDisallowedFields("id");
	    }

	    @GetMapping("/students/new")
	    public String initCreationForm(Map<String, Object> model) {
	        Student student = new Student();
	        model.put("student", student);
	        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	    }

	    @PostMapping("/students/new")
	    public String processCreationForm(@Valid Student student, BindingResult result) {
	        if (result.hasErrors()) {
	            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	        } else {
	            this.studentsRepo.save(student);
	            return "redirect:/students/" + student.getStudentId();
	        }
	    }

	    @GetMapping("/students/find")
	    public String initFindForm(Map<String, Object> model) {
	        model.put("student", new Student());
	        return "students/findStudents";
	    }

	    @GetMapping("/student")
	    public String processFindForm(Student student, BindingResult result, Map<String, Object> model) {

	        // allow parameterless GET request for /owners to return all records
	        if (student.getLastName() == null) {
	        	student.setLastName(""); // empty string signifies broadest possible search
	        }

	        // find owners by last name
	        Collection<Student> results = this.studentsRepo.findByLastName(student.getLastName());
	        if (results.isEmpty()) {
	            // no owners found
	            result.rejectValue("lastName", "notFound", "not found");
	            return "students/findStudents";
	        } else if (results.size() == 1) {
	            // 1 owner found
	            student = results.iterator().next();
	            return "redirect:/students/" + student.getStudentId();
	        } else {
	            // multiple owners found
	            model.put("selections", results);
	            return "students/studentsList";
	        }
	    }

	    @GetMapping("/students/{studentId}/edit")
	    public String initUpdateOwnerForm(@PathVariable("studentId") String studentId, Model model) {
	        Student student = this.studentsRepo.findById(studentId);
	        model.addAttribute(student);
	        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	    }

	    @PostMapping("/students/{studentId}/edit")
	    public String processUpdateOwnerForm(@Valid Student student, BindingResult result, @PathVariable("studentId") String studentId) {
	        if (result.hasErrors()) {
	            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	        } else {
	        	student.setStudentId(studentId);
	            this.studentsRepo.save(student);
	            return "redirect:/students/{studentId}";
	        }
	    }

	    /**
	     * Custom handler for displaying an owner.
	     *
	     * @param ownerId the ID of the owner to display
	     * @return a ModelMap with the model attributes for the view
	     */
	    @GetMapping("/students/{studentId}")
	    public ModelAndView showOwner(@PathVariable("studentId") String studentId) {
	        ModelAndView mav = new ModelAndView("students/studentDetails");
	        mav.addObject(this.studentsRepo.findById(studentId));
	        return mav;
	    }


}
