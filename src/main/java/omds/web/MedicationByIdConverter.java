package omds.web;

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
        return medicationRepo.findById(id);
    }
    
}
