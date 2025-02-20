package tour.gout_backend.tour;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tour.gout_backend.common.exception.EntityNotFoundException;
import tour.gout_backend.common.exception.enumeration.TourStatus;
import tour.gout_backend.tour.dto.TourRequest;
import tour.gout_backend.tour.dto.TourResponse;
import tour.gout_backend.tour.mapper.TourMapper;
import tour.gout_backend.tour.model.Tour;
import tour.gout_backend.tour.model.TourCount;
import tour.gout_backend.tour.repository.TourCountRepository;
import tour.gout_backend.tour.repository.TourRepository;
import tour.gout_backend.tourcompany.model.TourCompany;
import tour.gout_backend.tourcompany.repository.TourCompanyRepository;

@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {

    private final Logger logger = LoggerFactory.getLogger(TourServiceImpl.class);

    private final TourMapper tourMapper;
    private final TourRepository tourRepository;
    private final TourCompanyRepository tourCompanyRepository;
    private final TourCountRepository tourCountRepository;

    @Override
    public Tour createTour(TourRequest body) {
        var tourCompanyId = body.getTourCompanyId();
        TourCompany tourCompany = tourCompanyRepository.findById(tourCompanyId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Tour Company Id: %s not found", tourCompanyId)));
        var tourCount = new TourCount()
                .setAmount(0);
        var tour = new Tour()
                .setTitle(body.getTitle())
                .setDescription(body.getDescription())
                .setLocation(body.getLocation())
                .setNumberOfPeople(body.getNumberOfPeople())
                .setActivityDate(body.getActivityDate())
                .setStatus(TourStatus.PENDING.name())
                .setTourCompany(tourCompany)
                .setTourCount(tourCount);

        tourCount.setTour(tour);


        var newTour = tourRepository.save(tour);
        logger.debug("Tour has been created: {}", tour);
        return newTour;
    }

    @Override
    public Tour getTourById(int id) {
        return tourRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Tour Id: %s not found", id)));
    }

    @Override
    public Page<TourResponse> getPageTourDTO(Pageable pageable) {
        Page<Tour> tours = tourRepository.findAll(pageable);
//        List<TourResponse> tourResponses = tours.getContent().stream()
//                .map(tourMapper::toTourResponse)
//                .collect(Collectors.toList());

        return tours.map(tourMapper::toTourResponse);
    }

    @Override
    public Page<Tour> getPageTour(Pageable pageable) {
        return tourRepository.findAll(pageable);
    }
}
