package tour.gout_backend.wallet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_wallet")
public class UserWallet {
    @Id
    private Integer id;
    @NotNull
    private Integer userId;
    LocalDateTime lastUpdated;
    @NotNull
    private BigDecimal balance;
}
