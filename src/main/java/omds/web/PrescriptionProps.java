package omds.web;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix="intake.prescription")
@Data
public class PrescriptionProps {
    private int pageSize = 20;
}
