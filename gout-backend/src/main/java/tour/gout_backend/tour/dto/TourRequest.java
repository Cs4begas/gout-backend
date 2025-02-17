package tour.gout_backend.tour.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
public class TourRequest {
    @NotNull
    private Integer tourCompanyId;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String location;
    @NotNull
    private Integer numberOfPeople;
    @NotNull
    private LocalDateTime activityDate;
    private String status;
}
