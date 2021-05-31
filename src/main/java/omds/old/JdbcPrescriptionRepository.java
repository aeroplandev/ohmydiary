package old;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import omds.Intake;
import omds.Prescription;

@Repository
public class JdbcPrescriptionRepository implements PrescriptionRepository{

    private SimpleJdbcInsert prescriptionInserter;
    private SimpleJdbcInsert prescriptionIntakeInserter;
    private ObjectMapper objectMapper;

    @Autowired
    public JdbcPrescriptionRepository(JdbcTemplate jdbc){
        this.prescriptionInserter = new SimpleJdbcInsert(jdbc)
                                        .withTableName("Intake_Prescription")
                                        .usingGeneratedKeyColumns("id");
        this.prescriptionIntakeInserter = new SimpleJdbcInsert(jdbc)
                                                .withTableName("Intake_Prescription_Intakes");
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Prescription save(Prescription prescription) {
        prescription.setCreatedAt(new Date());
        long prescriptionId = savePrescriptionDetails(prescription);
        prescription.setId(prescriptionId);
        List<Intake> intakes = prescription.getIntakes();

        for(Intake intake : intakes){
            saveIntakeToPrescription(intake, prescriptionId);
        }
        return prescription;
    }

    private void saveIntakeToPrescription(Intake intake, long prescriptionId) {
        Map<String, Object> values = new HashMap<>();
        values.put("intakePrescription", prescriptionId);
        values.put("intake", intake.getId());
        prescriptionIntakeInserter.execute(values);
    }

    private long savePrescriptionDetails(Prescription prescription) {
        @SuppressWarnings("unchecked")
        Map<String, Object> values = 
            objectMapper.convertValue(prescription, Map.class);
        values.put("createdAt", prescription.getCreatedAt());
        
        long prescriptionId = prescriptionInserter.executeAndReturnKey(values).longValue();
        return prescriptionId;
    }
    
}
