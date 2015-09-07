package de.codenorm.certification.rest.resource;

import de.codenorm.certification.service.EmployeeWageService;
import de.codenorm.certification.web.rest.resource.WageResource;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import rest.model.WageDto;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by mahmut.can on 22.05.2015.
 */
public class WageResourceTest {

    @InjectMocks
    private WageResource wageResource;

    @Mock
    private EmployeeWageService employeeWageService;


    private MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(wageResource)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    public void shouldGetWageById() throws Exception {
        //Given
        WageDto wageDto = WageDto.WageDtoBuilder.aWageDto()
                .withId(33L)
                .build();
        when(employeeWageService.findWage(33L)).thenReturn(wageDto);
        //When
        ResultActions resultActions = mockMvc.perform(get("/api/employee/{employeeId}/wage/{wageId}", 42L, 33L));
        //Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(33)));
    }

    @Test
    public void shouldUpdateWageByIdAndBody() throws Exception {
        //Given
        WageDto wageDto = WageDto.WageDtoBuilder.aWageDto()
                .withId(33L)
                .withQuantity(3f)
                .build();
        when(employeeWageService.update(eq(33L), Matchers.any(WageDto.class))).thenReturn(wageDto);
        //When
        ResultActions resultActions = mockMvc.perform(put("/api/employee/{employeeId}/wage/{wageId}", 42L, 33L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(IntegrationTestUtil.convertObjectToJsonBytes(wageDto)));
        //Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(33)))
                .andExpect(jsonPath("$.quantity", is(3.0)));
        verify(employeeWageService).update(eq(33L), Matchers.any(WageDto.class));
    }

    @Test
    public void shouldSaveWage() throws Exception {
        //Given
        WageDto wageDto = WageDto.WageDtoBuilder.aWageDto()
                .withId(null)
                .build();

        when(employeeWageService.save(42L, Matchers.any(WageDto.class))).thenReturn(wageDto);
        //When
        ResultActions resultActions = mockMvc.perform(post("/api/employee/{employeeId}/wage", 42L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(IntegrationTestUtil.convertObjectToJsonBytes(wageDto)));
        //Then
        resultActions
                .andExpect(status().isOk());
        verify(employeeWageService).save(42L, Matchers.any(WageDto.class));
    }

    @Test
    public void shouldDeleteWageById() throws Exception {
        //Given

        //When
        ResultActions resultActions = mockMvc.perform(delete("/api/employee/{employeeId}/wage/{wageId}", 42L, 33L));
        //Then
        resultActions
                .andExpect(status().isOk());
        verify(employeeWageService).delete(33L);
    }
}
