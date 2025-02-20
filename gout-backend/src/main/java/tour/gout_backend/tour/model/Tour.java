package tour.gout_backend.tour.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import tour.gout_backend.tourcompany.model.TourCompany;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tour")
@Accessors(chain = true)
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class Tour implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private String location;
    private Integer numberOfPeople;
    private LocalDateTime activityDate;
    private String status;

    @OneToOne(mappedBy = "tour", cascade = CascadeType.ALL, optional = false)
    @JsonManagedReference
    private TourCount tourCount;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "tour_company_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonManagedReference
    private TourCompany tourCompany;
}
