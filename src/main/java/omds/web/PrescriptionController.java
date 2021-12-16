package omds.web;


import org.hibernate.cache.spi.entry.CollectionCacheEntry;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import omds.Prescription;
import omds.data.PrescriptionRepository;

@Slf4j
@RestController
@RequestMapping(path="/prescription", produces="application/json")
@CrossOrigin(origins="*")
public class PrescriptionController {
    
    private PrescriptionRepository prescriptionRepo;

    private PrescriptionProps props;

    public PrescriptionController(PrescriptionRepository prescriptionRepo, PrescriptionProps props){
        this.prescriptionRepo = prescriptionRepo;
        this.props = props;
    }

    @PutMapping("/{prescriptionId}")
    public Prescription putPrescription(@RequestBody Prescription prescriptionion){
       return  prescriptionRepo.save(prescriptionion);
    }

    @PatchMapping(path="/{prescriptionId}", consumes="application/json")
    public Prescription patchPrescription(@PathVariable("prescriptionId") Long prescriptionId
                                            , @RequestBody Prescription patch){
            Prescription prescription = prescriptionRepo.findById(prescriptionId).get();
            if(patch.getName() != null){
                prescription.setName(patch.getName());
            }
            return prescriptionRepo.save(prescription);
    }

    @DeleteMapping("prescriptionId")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deletePrescription(@PathVariable("prescriptionId") Long prescriptionId){
        try{
            prescriptionRepo.deleteById(prescriptionId);
        }catch(EmptyResultDataAccessException e){}
    }

}
