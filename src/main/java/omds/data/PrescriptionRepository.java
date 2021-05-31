package omds.data;

import org.springframework.data.repository.CrudRepository;

import omds.Prescription;

public interface PrescriptionRepository extends CrudRepository<Prescription, Long>{
    //Prescription save(Prescription prescription);
}
