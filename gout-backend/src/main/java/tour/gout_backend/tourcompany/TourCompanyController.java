package tour.gout_backend.tourcompany;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tour.gout_backend.tourcompany.model.TourCompany;
import tour.gout_backend.tourcompany.model.dto.RegisterTourCompanyRequest;

@RestController
@RequestMapping("/api/v1/tour-companies")
@RequiredArgsConstructor
public class TourCompanyController {

    private final Logger logger = LoggerFactory.getLogger(TourCompanyController.class);
    private final TourCompanyService tourCompanyService;

    @PostMapping
    public ResponseEntity<TourCompany> registerNewTourCompany(@RequestBody @Valid RegisterTourCompanyRequest body) {
        var tourCompany = tourCompanyService.registerTourCompany(body);
        return ResponseEntity.ok(tourCompany);
    }

    @PatchMapping("/{id}/approve")
    public ResponseEntity<TourCompany> approvedCompany(@PathVariable Integer id) {
        var approvedTourCompany = tourCompanyService.approvedTourCompany(id);
        logger.info("[approvedCompany] company id: {} is approved", id);
        return ResponseEntity.ok(approvedTourCompany);
    }
}
