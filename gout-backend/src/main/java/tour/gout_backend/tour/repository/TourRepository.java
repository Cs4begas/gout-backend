package tour.gout_backend.tour.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import tour.gout_backend.tour.model.Tour;

public interface TourRepository extends JpaRepository<Tour, Integer> {
    Page<Tour> findAll(Pageable pageable);
}
