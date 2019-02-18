package ph.edu.celp.dev.studentrecordmanagement.system;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
class WelcomeController {

    @GetMapping("/")
    public ModelAndView welcome(Model model) {
    	model.addAttribute("message", "Welcome to Centro Escolar Las Piñas Student Record Management");
        return new ModelAndView("welcome");
    }
}
