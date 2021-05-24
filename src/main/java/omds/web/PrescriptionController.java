package omds.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import omds.Prescription;

@Slf4j
@Controller
@RequestMapping("/prescriptions")
public class PrescriptionController {
    
    @GetMapping("/current")
    public String prescriptionForm(Model model){
        model.addAttribute("prescription", new Prescription());
        return "prescriptionForm";
    }

    @PostMapping
    public String processPrescription(@Valid Prescription prescription, Errors errors){
        if(errors.hasErrors()){
            return "prescriptionForm";
        }
        log.info("Prescription submitted: " + prescription);
        return "redirect:/";
    }
}
