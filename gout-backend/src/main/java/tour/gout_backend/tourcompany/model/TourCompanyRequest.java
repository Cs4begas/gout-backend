package tour.gout_backend.tourcompany.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TourCompanyRequest {
    private Integer id;
    @NotBlank
    private String name;
    private String status;
}
