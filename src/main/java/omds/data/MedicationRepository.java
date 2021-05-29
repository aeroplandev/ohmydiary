package omds.data;

import omds.Medication;

public interface MedicationRepository {
    Iterable<Medication> findAll();
    Medication findById(String id);
    Medication save(Medication medication);
}
