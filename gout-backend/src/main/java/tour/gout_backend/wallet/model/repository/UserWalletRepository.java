package tour.gout_backend.wallet.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tour.gout_backend.wallet.model.UserWallet;

import java.util.Optional;

public interface UserWalletRepository extends JpaRepository<UserWallet, Integer> {
    Optional<UserWallet> findOneByUserId(Integer userId);
}
