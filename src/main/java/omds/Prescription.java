package omds;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class Prescription {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "starting date is required")
    @Pattern(regexp="^([1-2][0-9][0-9][0-9])([\\/])(0[1-9]|1[0-2])([\\/])([0-9][0-9])$", message = "Must be formatted YYYY/MM/DD")
    private String startDate;
    @Min(value=1, message = "intake days should at least 1")
    private int intakeDays;
}
