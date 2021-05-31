package old;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import omds.Medication;

@Repository
public class JdbcMedicationRepository implements MedicationRepository{
    private JdbcTemplate jdbc;

    @Autowired
    public JdbcMedicationRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public Iterable<Medication> findAll() {
        return jdbc.query("select id, name, type from Medication", this::mapRowToMedication);
    }

    @Override
    public Medication findById(String id) {
        return jdbc.queryForObject("select id, name, type from Medication where id=?"
        , this::mapRowToMedication, id);
    }

    @Override
    public Medication save(Medication medication) {
        jdbc.update("insert into Medication (id, name, type) values (?,?,?)"
        , medication.getId()
        , medication.getName()
        , medication.getType().toString());
        return medication;
    }
    
    private Medication mapRowToMedication(ResultSet rs, int rowNum)
        throws SQLException {
            return new Medication(rs.getString("id"), rs.getString("name"), true, Medication.Type.valueOf(rs.getString("type")), 1.0, 1.0);
        }
}
