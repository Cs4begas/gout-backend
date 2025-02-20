package tour.gout_backend.tourcompany;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tour.gout_backend.common.exception.EntityNotFoundException;
import tour.gout_backend.common.exception.enumeration.TourCompanyStatus;
import tour.gout_backend.tourcompany.model.TourCompany;
import tour.gout_backend.tourcompany.model.dto.RegisterTourCompanyRequest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TourCompanyController.class)
public class TourCompanyControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TourCompanyService tourCompanyService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void whenCreateTourCompanyThenSuccessful() throws Exception {
        var mockTourCompany = new TourCompany()
                .setId(1)
                .setName("Mart Tour")
                .setStatus(TourCompanyStatus.WAITING.name());
        when(tourCompanyService.registerTourCompany(any(RegisterTourCompanyRequest.class)))
                .thenReturn(mockTourCompany);
        var payload = new RegisterTourCompanyRequest(null, "Mart Tour", "mart", "123456789", null);

        mockMvc.perform(
                        post("/api/v1/tour-companies")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

    }

    @Test
    void whenApproveTourCompanyThenSuccessful() throws Exception {
        var mockTourCompany = new TourCompany()
                .setId(1)
                .setName("Mart Tour")
                .setStatus(TourCompanyStatus.APPROVED.name());
        when(tourCompanyService.approvedTourCompany(anyInt()))
                .thenReturn(mockTourCompany);
        mockMvc.perform(
                        patch(String.format("/api/v1/tour-companies/%d/approve", 1))
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value(TourCompanyStatus.APPROVED.name()));
    }

    @Test
    void whenApproveTourButCompanyNotFoundThenReturn404() throws Exception {
        when(tourCompanyService.approvedTourCompany(anyInt()))
                .thenThrow(new EntityNotFoundException());
        mockMvc.perform(patch(String.format("/api/v1/tour-companies/%d/approve", 1)))
                .andExpect(status().isNotFound());
    }
}
