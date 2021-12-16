package omds.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import omds.Prescription;
import omds.data.PrescriptionRepository;

@Slf4j
@Controller
@RequestMapping("/prescriptions")
public class PrescriptionController {
    
    private PrescriptionRepository prescriptionRepo;

    public PrescriptionController(PrescriptionRepository prescriptionRepo){
        this.prescriptionRepo = prescriptionRepo;
    }

    @GetMapping("/current")
    public String prescriptionForm(Model model){
        model.addAttribute("prescription", new Prescription());
        return "prescriptionForm";
    }

    @PostMapping
    public String processPrescription(@Valid Prescription prescription
                                        , Errors errors
                                        , SessionStatus sessionStatus){
        if(errors.hasErrors()){
            return "prescriptionForm";
        }
        //log.info("Prescription submitted: " + prescription);

        prescriptionRepo.save(prescription);
        sessionStatus.setComplete();
        
        return "redirect:/";
    }
}
