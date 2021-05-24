package omds;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Intake {

    @NotNull
    @Size(min=5, message = "Name must be at least 5 characters long")
    private String name;
    @Size(min=1, message = "You must choose at least 1 medication")
    private List<String> medications;
    
}
