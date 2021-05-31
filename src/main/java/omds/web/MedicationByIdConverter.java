package omds.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import omds.Medication;
import omds.data.MedicationRepository;

@Component
public class MedicationByIdConverter implements Converter<String, Medication>{

    private MedicationRepository medicationRepo;

    @Autowired
    public MedicationByIdConverter(MedicationRepository medicationRepo){
        this.medicationRepo = medicationRepo;
    }

    @Override
    public Medication convert(String id) {
        Optional<Medication> optioanlMedication = medicationRepo.findById(id);
        return optioanlMedication.isPresent() ?
                    optioanlMedication.get() : null;
        //return medicationRepo.findById(id);
    }
    
}
