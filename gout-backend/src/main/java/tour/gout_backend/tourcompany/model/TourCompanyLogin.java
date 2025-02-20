package tour.gout_backend.tourcompany.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class TourCompanyLogin{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @NotNull
        private String username;
        @NotNull
        private String password;

        @OneToOne
        @JoinColumn(name = "tour_company_id", referencedColumnName = "id")
        @JsonBackReference
        private TourCompany tourCompany;

}
