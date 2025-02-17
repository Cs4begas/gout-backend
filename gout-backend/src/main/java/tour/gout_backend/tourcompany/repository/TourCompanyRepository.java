package tour.gout_backend.tourcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tour.gout_backend.tourcompany.model.TourCompany;

@Repository
public interface TourCompanyRepository extends JpaRepository<TourCompany, Integer> {
}
