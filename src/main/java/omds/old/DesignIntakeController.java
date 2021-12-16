package omds.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import omds.Intake;
import omds.Medication;
import omds.Prescription;
import omds.Medication.Type;
import omds.data.IntakeRepository;
import omds.data.MedicationRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("prescription")
public class DesignIntakeController {
    private final MedicationRepository medicationRepo;
    private IntakeRepository intakeRepo;

    @Autowired
    public DesignIntakeController(MedicationRepository medicationRepo, IntakeRepository intakeRepo){
        this.medicationRepo = medicationRepo;
        this.intakeRepo = intakeRepo;
    }

    @GetMapping
    public String showDesignForm(Model model) {

        List<Medication> medications = new ArrayList<>();
        medicationRepo.findAll().forEach(i -> medications.add(i));

        Type[] types = Medication.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(medications, type));
        }

        model.addAttribute("intake", new Intake());
    
        return "design";
    }
    
    private List<Medication> filterByType(List<Medication> medications, Type type) {
        return medications
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList()); 
    }

    @ModelAttribute(name ="prescription")
    public Prescription prescription(){
        return new Prescription();
    }

    @ModelAttribute(name = "intake")
    public Intake intake(){
        return new Intake();
    }

    @PostMapping
    public String processDesign(@Valid Intake design
                                , Errors errors
                                , @ModelAttribute Prescription prescription) {
        if(errors.hasErrors()){
            return "design";
        }
        
        //log.info("processing design: " + design);

        Intake intake = intakeRepo.save(design);
        prescription.addDesign(intake);
        
        return "redirect:/prescriptions/current";
    }
}
