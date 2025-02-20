package tour.gout_backend.tour;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tour.gout_backend.common.exception.EntityNotFoundException;
import tour.gout_backend.common.exception.enumeration.TourStatus;
import tour.gout_backend.tour.dto.TourRequest;
import tour.gout_backend.tour.dto.TourResponse;
import tour.gout_backend.tour.model.Tour;
import tour.gout_backend.tourcompany.model.TourCompany;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TourController.class)
public class TourControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TourService tourService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void whenGetPageTourThenSuccessful() throws Exception {
        var tour = new Tour()
                .setId(1)
                .setTitle("Camping")
                .setDescription("Campaign 3 days 2 night")
                .setLocation("Forest")
                .setNumberOfPeople(10)
                .setActivityDate(LocalDateTime.now().plusDays(5))
                .setStatus(TourStatus.PENDING.name());
        List<Tour> tours = List.of(tour);
        Page<Tour> pageTours = new PageImpl<>(tours);
        when(tourService.getPageTour(any(Pageable.class)))
                .thenReturn(pageTours);

        mockMvc.perform(
                        get("/api/v1/tours?page=0&size=2&sortField=id&sortDirection=asc")
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    void whenGetPageTourDTOThenSuccessful() throws Exception {
        var tour = new TourResponse()
                .setId(1)
                .setTitle("Camping")
                .setDescription("Campaign 3 days 2 night")
                .setLocation("Forest")
                .setNumberOfPeople(10)
                .setActivityDate(LocalDateTime.now().plusDays(5))
                .setStatus(TourStatus.PENDING.name());
        List<TourResponse> tours = List.of(tour);
        Page<TourResponse> pageTours = new PageImpl<>(tours);
        when(tourService.getPageTourDTO(any(Pageable.class)))
                .thenReturn(pageTours);

        mockMvc.perform(
                        get("/api/v1/tours/dto?page=0&size=2&sortField=id&sortDirection=asc")
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }


    @Test
    void whenGetPageTourButForgotRequiredQueryString() throws Exception {
        mockMvc.perform(
                        get("/api/v1/tours?sortField=id&sortDirection=asc")
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenCreateTourThenSuccessful() throws Exception {
        var activityDate = LocalDateTime.now().plus(Duration.ofDays(5));
        var payload = new TourRequest(
                1,
                "Camping",
                "Campaign 3 days 2 night",
                "Forest",
                10,
                activityDate,
                TourStatus.PENDING.name());

        var tourCompany = new TourCompany().setId(1);

        var tour = new Tour().setId(1)
                .setTourCompany(tourCompany)
                .setDescription("Campaign 3 days 2 night")
                .setLocation("Forest")
                .setNumberOfPeople(10)
                .setActivityDate(activityDate)
                .setStatus(TourStatus.PENDING.name());

        when(tourService.createTour(any(TourRequest.class)))
                .thenReturn(tour);

        mockMvc.perform(
                        post("/api/v1/tours")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void whenGetTourByIdThenSuccessful() throws Exception {
        var tour = new Tour()
                .setId(1)
                .setTourCompany(new TourCompany().setId(1))
                .setTitle("Camping")
                .setDescription("Campaign 3 days 2 night")
                .setLocation("Forest")
                .setNumberOfPeople(10)
                .setActivityDate(LocalDateTime.now().plus(Duration.ofDays(5)))
                .setStatus(TourStatus.PENDING.name());

        when(tourService.getTourById(1))
                .thenReturn(tour);

        mockMvc.perform(
                        get("/api/v1/tours/1")
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    void whenCreateTourButMissingSomeFieldsThen400() throws Exception {
        var payload = new TourRequest()
                .setTourCompanyId(1)
                .setTitle("Camping")
                .setDescription("Campaign 3 days 2 night")
                .setLocation("Forest")
                .setNumberOfPeople(10)
                .setActivityDate(LocalDateTime.now().plus(Duration.ofDays(5)))
                .setStatus(TourStatus.PENDING.name());

        mockMvc.perform(
                        post("/api/v1/tours")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenGetTourByIdButCompanyNotFoundThenReturn404() throws Exception {
        when(tourService.getTourById(anyInt()))
                .thenThrow(new EntityNotFoundException());
        mockMvc.perform(get(String.format("/api/v1/tours/%d", 1)))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenGetTourByIdButServerErrorThenReturn500() throws Exception {
        when(tourService.getTourById(anyInt()))
                .thenThrow(new RuntimeException());
        mockMvc.perform(get(String.format("/api/v1/tours/%d", 1)))
                .andExpect(status().isInternalServerError());
    }

}
