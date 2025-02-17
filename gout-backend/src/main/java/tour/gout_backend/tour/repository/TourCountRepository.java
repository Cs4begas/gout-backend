package tour.gout_backend.tour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tour.gout_backend.tour.model.TourCount;

import java.util.Optional;

public interface TourCountRepository extends JpaRepository<TourCount, Integer> {
    Optional<TourCount> findOneByTourId(Integer tourId);
}
