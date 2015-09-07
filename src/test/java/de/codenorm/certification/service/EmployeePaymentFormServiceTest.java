package de.codenorm.certification.service;

import de.codenorm.certification.domain.EmployeePaymentForm;
import de.codenorm.certification.repository.EmployeePaymentFormRepo;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;
import rest.model.EmployeePaymentFormDto;

import java.math.BigDecimal;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmployeePaymentFormServiceTest {

    @Mock
    private EmployeePaymentFormRepo employeePaymentFormRepo;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeePaymentFormService employeePaymentFormService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateNewFormWithountAnyFormBefore() {
        //Given

        long employeeId = 42L;
        EmployeePaymentFormDto dto = new EmployeePaymentFormDto();
        EmployeePaymentForm currentEmployeePaymentForm = null;

        when(employeePaymentFormRepo.findOne(any(Specification.class))).thenReturn(currentEmployeePaymentForm);
        //When
        employeePaymentFormService.saveOrUpdateWageForm(employeeId, dto);
        ArgumentCaptor<EmployeePaymentForm> argumentCaptor = ArgumentCaptor.forClass(EmployeePaymentForm.class);
        verify(employeePaymentFormRepo).save(argumentCaptor.capture());

        //Then
        EmployeePaymentForm employeePaymentForm = argumentCaptor.getValue();
        verify(employeePaymentFormRepo).save(employeePaymentForm);
    }


    @Test
    public void shouldUpdateExitingForm() {
        //Given

        long employeeId = 42L;
        EmployeePaymentFormDto dto = new EmployeePaymentFormDto();
        dto.setPaymentMethod(EmployeePaymentForm.PaymentMethod.HOURLY);
        dto.setRate(new BigDecimal(10));

        EmployeePaymentForm currentEmployeePaymentForm = new EmployeePaymentForm();
        currentEmployeePaymentForm.setPaymentMethod(EmployeePaymentForm.PaymentMethod.DAILY);
        currentEmployeePaymentForm.setRate(new BigDecimal(80d));

        when(employeePaymentFormRepo.findOne(any(Specification.class))).thenReturn(currentEmployeePaymentForm);
        //When
        employeePaymentFormService.saveOrUpdateWageForm(employeeId, dto);

        //Then
        assertThat(currentEmployeePaymentForm.getPaymentMethod(), equalTo(EmployeePaymentForm.PaymentMethod.HOURLY));
        assertThat(currentEmployeePaymentForm.getRate(), equalTo(dto.getRate()));

    }
}
