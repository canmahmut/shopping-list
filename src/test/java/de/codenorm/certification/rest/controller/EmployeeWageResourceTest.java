package de.codenorm.certification.rest.controller;


import de.codenorm.certification.service.EmployeeWageService;
import de.codenorm.certification.web.rest.controller.EmployeeWageResource;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EmployeeWageResourceTest {

    @InjectMocks
    private EmployeeWageResource employeeWageResource;

    @Mock
    private EmployeeWageService employeeWageService;


    private MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(employeeWageResource)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }


    @Test
    public void shouldCallFixWages() throws Exception {
        mockMvc.perform(get("/api/employee/{employeeId}/fixWage", "1").param("filter[untilDate]", "01-1972-1228T12:00:01.000Z"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/employee/{employeeId}/fixWage", "1").param("filter[untilDate]", "2015-01-13T23:00:00.000Z"))
                .andExpect(status().isOk());

    }


}