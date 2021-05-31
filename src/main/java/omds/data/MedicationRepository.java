package omds.data;

import org.springframework.data.repository.CrudRepository;

import omds.Medication;

public interface MedicationRepository extends CrudRepository<Medication, String>{
    /*
    Iterable<Medication> findAll();
    Medication findById(String id);
    Medication save(Medication medication);
    */
}
