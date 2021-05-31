package omds;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
@Entity
@Table(name = "Intake_Prescription")
public class Prescription implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date createdat;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "starting date is required")
    @Pattern(regexp="^([1-2][0-9][0-9][0-9])([\\/])(0[1-9]|1[0-2])([\\/])([0-9][0-9])$", message = "Must be formatted YYYY/MM/DD")
    private String startDate;
    
    @Min(value=1, message = "intake days should at least 1")
    private int intakeDays;

    @ManyToMany(targetEntity = Intake.class)
    private List<Intake> intakes = new ArrayList<>();

    public void addDesign(Intake design) {
        this.intakes.add(design);
    }

    @PrePersist
    void createdAt(){
        this.createdat = new Date();
    }
}
