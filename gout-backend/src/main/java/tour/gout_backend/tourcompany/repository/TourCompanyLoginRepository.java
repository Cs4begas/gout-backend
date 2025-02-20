package tour.gout_backend.tourcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tour.gout_backend.tourcompany.model.TourCompanyLogin;

import java.util.Optional;

public interface TourCompanyLoginRepository extends JpaRepository<TourCompanyLogin, Integer> {
    Optional<TourCompanyLogin> findOneByUsername(String username);
    Optional<TourCompanyLogin> findOneByTourCompanyId(Integer tourCompanyId);
}
