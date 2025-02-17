package tour.gout_backend.tour;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tour.gout_backend.tour.dto.TourRequest;
import tour.gout_backend.tour.dto.TourResponse;
import tour.gout_backend.tour.model.Tour;

public interface TourService {

    Tour createTour(TourRequest body);

    Tour getTourById(int id);

    Page<TourResponse> getPageTourDTO(Pageable pageable);

    Page<Tour> getPageTour(Pageable pageable);
}
