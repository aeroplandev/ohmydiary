package omds.data;

import org.springframework.data.repository.CrudRepository;

import omds.Intake;

public interface IntakeRepository extends CrudRepository<Intake, Long>{
    //Intake save(Intake design);
}
