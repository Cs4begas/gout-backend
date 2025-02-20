package tour.gout_backend.wallet.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import tour.gout_backend.tourcompany.model.TourCompany;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tour_company_wallet")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class TourCompanyWallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime lastUpdated;
    @NotNull
    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name = "tour_company_id", referencedColumnName = "id")
    private TourCompany tourCompany;

}
