package tour.gout_backend.tour.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class TourCountResponse {
    private Integer id;
    private Integer amount;
}
