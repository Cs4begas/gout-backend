package tour.gout_backend.tour;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tour.gout_backend.tour.dto.TourRequest;
import tour.gout_backend.tour.dto.TourResponse;
import tour.gout_backend.tour.model.Tour;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/tours")
@RequiredArgsConstructor
public class TourController {

    private final Logger logger = LoggerFactory.getLogger(TourController.class);
    private final TourService tourService;

    @GetMapping("/dto")
    public Page<TourResponse> getToursDTO(
            @RequestParam(required = true, defaultValue = "1") int page,
            @RequestParam(required = true) int size,
            @RequestParam(required = true) String sortField,
            @RequestParam(required = true) String sortDirection) {
        // 1-100 tours
        // Page - 2
        // Size - 20 [1-20][21-40][41-60][61-80][81-100] <- ASC
        // Sort - ASC, DESC
        Sort sort = Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sortField);
        Pageable pageable = PageRequest.of(page, size, sort);
        return tourService.getPageTourDTO(pageable);
    }


    @GetMapping
    public Page<Tour> getTours(
            @RequestParam(required = true, defaultValue = "1") int page,
            @RequestParam(required = true) int size,
            @RequestParam(required = true) String sortField,
            @RequestParam(required = true) String sortDirection) {
        // 1-100 tours
        // Page - 2
        // Size - 20 [1-20][21-40][41-60][61-80][81-100] <- ASC
        // Sort - ASC, DESC
        Sort sort = Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sortField);
        Pageable pageable = PageRequest.of(page, size, sort);
        return tourService.getPageTour(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tour> getTourById(@PathVariable int id) {
        logger.info("Get tourId: {}", id);
        return ResponseEntity.ok(tourService.getTourById(id));
    }

    @PostMapping
    public ResponseEntity<Tour> createTour(@RequestBody @Validated TourRequest body) {
        var newTour = tourService.createTour(body);
        var location = String.format("http://localhost/api/v1/tours/%d", newTour.getId());
        return ResponseEntity.created(URI.create(location)).body(newTour);
    }
}
