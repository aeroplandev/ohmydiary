package omds;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name="Intake")
public class Intake {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date createdat;
    
    @NotNull
    @Size(min=5, message = "Name must be at least 5 characters long")
    private String name;

    @ManyToMany(targetEntity = Medication.class)
    @Size(min=1, message = "You must choose at least 1 medication")
    private List<Medication> medications;

    @PrePersist
    void createdAt(){
        this.createdat = new Date();
    }
}
