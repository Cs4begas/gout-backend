package tour.gout_backend.tour;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import tour.gout_backend.common.exception.EntityNotFoundException;
import tour.gout_backend.common.exception.enumeration.TourCompanyStatus;
import tour.gout_backend.common.exception.enumeration.TourStatus;
import tour.gout_backend.tour.dto.TourRequest;
import tour.gout_backend.tour.model.Tour;
import tour.gout_backend.tour.repository.TourCountRepository;
import tour.gout_backend.tour.repository.TourRepository;
import tour.gout_backend.tourcompany.model.TourCompany;
import tour.gout_backend.tourcompany.repository.TourCompanyRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TourServiceTest {

    @InjectMocks
    private TourServiceImpl tourService;
    @Mock
    private TourRepository tourRepository;
    @Mock
    private TourCompanyRepository tourCompanyRepository;
    @Mock
    private TourCountRepository tourCountRepository;

    @Test
    void whenCreateTourThenReturnSuccess() {
        var activityDate = LocalDateTime.now().plus(Duration.ofDays(5));
        var payload = new TourRequest()
                .setTourCompanyId(1)
                .setTitle("Camping")
                .setDescription("Campaign 3 days 2 night")
                .setLocation("Forest")
                .setNumberOfPeople(10)
                .setActivityDate(activityDate)
                .setStatus(TourStatus.PENDING.name());

        var mockTourCompany = new TourCompany()
                .setId(1)
                .setName("Mart Tour")
                .setStatus(TourCompanyStatus.WAITING.name());
        when(tourCompanyRepository.findById(payload.getTourCompanyId()))
                .thenReturn(Optional.of(mockTourCompany));

        var tour = new Tour()
                .setId(1)
                .setTourCompany(mockTourCompany)
                .setTitle("Camping")
                .setDescription("Campaign 3 days 2 night")
                .setLocation("Forest")
                .setNumberOfPeople(10)
                .setActivityDate(activityDate)
                .setStatus(TourStatus.PENDING.name());
        when(tourRepository.save(any(Tour.class)))
                .thenReturn(tour);

        var actual = tourService.createTour(payload);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(tour.getId(), actual.getId());
        Assertions.assertEquals(tour.getTourCompany().getId(), actual.getTourCompany().getId());
        Assertions.assertEquals(tour.getTitle(), actual.getTitle());
        Assertions.assertEquals(tour.getDescription(), actual.getDescription());
        Assertions.assertEquals(tour.getLocation(), actual.getLocation());
        Assertions.assertEquals(tour.getNumberOfPeople(), actual.getNumberOfPeople());
        Assertions.assertEquals(tour.getActivityDate(), actual.getActivityDate());
        Assertions.assertEquals(tour.getStatus(), actual.getStatus());
        Assertions.assertEquals(tour.getTitle(), actual.getTitle());
        Assertions.assertEquals(tour.getDescription(), actual.getDescription());
        Assertions.assertEquals(tour.getLocation(), actual.getLocation());
        Assertions.assertEquals(tour.getNumberOfPeople(), actual.getNumberOfPeople());
        Assertions.assertEquals(tour.getActivityDate(), actual.getActivityDate());
        Assertions.assertEquals(tour.getStatus(), actual.getStatus());
    }

    @Test
    void whenCreateTourButCompanyNotFoundThenReturnNotFound() {
        var payload = new TourRequest()
                .setTourCompanyId(1)
                .setTitle("Camping")
                .setDescription("Campaign 3 days 2 night")
                .setLocation("Forest")
                .setNumberOfPeople(10)
                .setActivityDate(LocalDateTime.now().plus(Duration.ofDays(5)))
                .setStatus(TourStatus.PENDING.name());
        when(tourCompanyRepository.findById(anyInt()))
                .thenThrow(new EntityNotFoundException(String.format("Tour Company Id: %s not found", 1)));
        Assertions.assertThrows(EntityNotFoundException.class, () -> tourService.createTour(payload));
    }

    @Test
    void whenGetTourByIdThenReturnSuccess() {
        var tour = new Tour();
        tour.setId(1);
        tour.setTourCompany(new TourCompany().setId(1));
        tour.setTitle("Camping");
        tour.setDescription("Campaign 3 days 2 night");
        tour.setLocation("Forest");
        tour.setNumberOfPeople(10);
        tour.setActivityDate(LocalDateTime.now().plus(Duration.ofDays(5)));
        tour.setStatus(TourStatus.PENDING.name());

        when(tourRepository.findById(anyInt()))
                .thenReturn(Optional.of(tour));

        var actual = tourService.getTourById(1);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(tour.getId(), actual.getId());
        Assertions.assertEquals(tour.getTourCompany().getId(), actual.getTourCompany().getId());
        Assertions.assertEquals(tour.getTitle(), actual.getTitle());
        Assertions.assertEquals(tour.getDescription(), actual.getDescription());
        Assertions.assertEquals(tour.getLocation(), actual.getLocation());
        Assertions.assertEquals(tour.getNumberOfPeople(), actual.getNumberOfPeople());
        Assertions.assertEquals(tour.getActivityDate(), actual.getActivityDate());
        Assertions.assertEquals(tour.getStatus(), actual.getStatus());
    }

    @Test
    void whenGetTourByIdThenReturnNotFound() {
        when(tourRepository.findById(anyInt()))
                .thenThrow(new EntityNotFoundException(String.format("Tour Company Id: %s not found", 1)));
        Assertions.assertThrows(EntityNotFoundException.class, () -> tourService.getTourById(1));
    }

    @Test
    void whenGetPageTourThenReturnSuccess() {
        List<Tour> tours = List.of();
        Page<Tour> pageTours = new PageImpl<>(tours);

        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(0, 5, sort);
        when(tourRepository.findAll(pageable))
                .thenReturn(pageTours);

        var actual = tourService.getPageTour(pageable);
        Assertions.assertTrue(actual.getContent().isEmpty());
    }
}
