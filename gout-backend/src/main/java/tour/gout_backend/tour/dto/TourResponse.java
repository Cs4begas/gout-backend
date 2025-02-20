package tour.gout_backend.tour.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import tour.gout_backend.tourcompany.model.dto.TourCompanyResponse;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Accessors(chain = true)
public class TourResponse {
    private Integer id;
    private String title;
    private String description;
    private String location;
    private Integer numberOfPeople;
    private LocalDateTime activityDate;
    private String status;

    private TourCompanyResponse tourCompany;
    private TourCountResponse tourCount;


//    public static TourResponse convertToTourResponse(Tour tour) {
//        TourResponse tourResponse = new TourResponse();
//        tourResponse.setId(tour.getId());
//        tourResponse.setTitle(tour.getTitle());
//        tourResponse.setDescription(tour.getDescription());
//        tourResponse.setLocation(tour.getLocation());
//        tourResponse.setNumberOfPeople(tour.getNumberOfPeople());
//        tourResponse.setActivityDate(tour.getActivityDate());
//        tourResponse.setStatus(tour.getStatus());
//
//        // Handle TourCount (important to avoid nulls and lazy loading issues)
//        if (tour.getTourCount()!= null) {
//            TourCountResponse tourCountResponse = new TourCountResponse();
//            tourCountResponse.setId(tour.getTourCount().getId());
//            tourCountResponse.setAmount(tour.getTourCount().getAmount());
//            tourResponse.setTourCount(tourCountResponse);
//        }
//
//        if (tour.getTourCompany() != null) {
//            TourCompanyResponse tourCompanyResponse = new TourCompanyResponse(tour.getTourCompany());
//            tourResponse.setTourCompany(tourCompanyResponse);
//        }
//
//
//        return tourResponse;
//    }

}
