package tour.gout_backend.tourcompany.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import tour.gout_backend.tour.model.Tour;
import tour.gout_backend.wallet.model.TourCompanyWallet;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tour_company")
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TourCompany implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @NotNull
        private String name;
        @NotNull
        private String status;


        @OneToOne(mappedBy = "tourCompany", cascade = CascadeType.ALL)
        @JsonManagedReference
        private TourCompanyLogin tourCompanyLogin;

        @OneToOne(mappedBy = "tourCompany", cascade = CascadeType.ALL)
        @JsonManagedReference
        private TourCompanyWallet tourCompanyWallet;

        @OneToMany(mappedBy = "tourCompany")
        @JsonBackReference
        private List<Tour> tourList;

}