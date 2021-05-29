package omds.data;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import omds.Intake;
import omds.Medication;

@Repository
public class JdbcIntakeRepository implements IntakeRepository{

    private JdbcTemplate jdbc;

    public JdbcIntakeRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public Intake save(Intake intake) {
        long intakeId = saveIntakeInfo(intake);
        intake.setId(intakeId);
        for (Medication medication : intake.getMedications()){
            saveMedicationToIntake(medication, intakeId);
        }
        return intake;
    }

    private void saveMedicationToIntake(Medication medication, long intakeId) {
        jdbc.update(
            "insert into Intake_Medications (intake, medication)"
            + " values (?,?)"
            , intakeId, medication.getId());
    }

    private long saveIntakeInfo(Intake intake) {
        intake.setCreatedAt(new Date());
        PreparedStatementCreator psc = 
            new PreparedStatementCreatorFactory(
                "insert into Intake (name, createdAt) values (?,?)"
                , Types.VARCHAR, Types.TIMESTAMP
            ).newPreparedStatementCreator(
                Arrays.asList(
                    intake.getName()
                    , new Timestamp(intake.getCreatedAt().getTime())
                )
            );
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);
        return keyHolder.getKey().longValue();
    }
    
}
