package omds.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import omds.Intake;
import omds.Medication;
import omds.Medication.Type;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignIntakeController {
    @GetMapping
    public String showDesignForm(Model model) {

        List<Medication> medications = Arrays.asList(
            new Medication("sb", "solondo", true, Type.AdrenalHormone, 5, 2.5)
            , new Medication("hx", "haloxin", true, Type.Antiprotozoal, 200, 1)
            , new Medication("lb", "labeone", true, Type.PepticUlcer, 10, 1)
            , new Medication("ap", "aspirin", true, Type.CirculatorySystem, 100, 1)
            , new Medication("nx", "naxenf", false, Type.AntiinflammatoryAnalgesic, 500, 1)
        );

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

    @PostMapping
    public String processDesign(@Valid Intake design, Errors errors) {
        if(errors.hasErrors()){
            return "design";
        }
        log.info("processing design: " + design);
        return "redirect:/prescriptions/current";
    }
}
