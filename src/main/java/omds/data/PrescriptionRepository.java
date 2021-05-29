package omds.data;

import omds.Prescription;

public interface PrescriptionRepository {
    Prescription save(Prescription prescription);
}
