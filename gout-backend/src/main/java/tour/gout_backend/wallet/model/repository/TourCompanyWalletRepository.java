package tour.gout_backend.wallet.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tour.gout_backend.wallet.model.TourCompanyWallet;

import java.util.Optional;

public interface TourCompanyWalletRepository extends JpaRepository<TourCompanyWallet, Integer> {
    Optional<TourCompanyWallet> findByTourCompanyId(Integer tourCompanyId);
}
