package de.codenorm.certification.service;

import de.codenorm.certification.config.OrikaMapper;
import de.codenorm.certification.domain.EmployeePaymentForm;
import de.codenorm.certification.domain.Wage;
import de.codenorm.certification.domain.WorkStation;
import de.codenorm.certification.repository.EmployeePaymentFormRepo;
import de.codenorm.certification.repository.WageRepo;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.data.jpa.domain.Specification;
import rest.model.EmployeePaymentFormDto;
import rest.model.WageDto;
import rest.model.WorkStationDto;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by can on 26.05.15.
 */


public class EmployeeWageServiceTest {

    private Wage originWage;
    private WageDto originWageDto;

    @Mock
    private WageRepo wageRepo;

    @Mock
    private EmployeePaymentFormRepo employeePaymentFormRepo;

    @InjectMocks
    private EmployeeWageService employeeWageService;

    @Spy
    private OrikaMapper orikaMapper = new OrikaMapper();

    @Before
    public void setUp() {
        originWage = Wage.WageBuilder.aWage()
                .withId(1L)
                .withQuantity(4f)
                .withWorkStation(WorkStation.WorkStationBuilder.aWorkStation()
                        .withId(2L)
                        .build())
                .withPaymentMethod(EmployeePaymentForm.PaymentMethod.DAILY)
                .withEntryDate(DateTime.parse("2009-01-01").toDate())
                .build();

        originWageDto = WageDto.WageDtoBuilder.aWageDto()
                .withId(1L)
                .withQuantity(4f)
                .withWorkStationDto(WorkStationDto.WorkStationDtoBuilder.aWorkStationDto()
                        .withId(2L)
                        .build())
                .withPaymentMethod(EmployeePaymentForm.PaymentMethod.DAILY)
                .withEntryDate(DateTime.parse("2009-01-01").toDate())
                .build();

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void mapWageToDto() {
        //Given
        //When
        WageDto dto = orikaMapper.map(originWage, WageDto.class);
        //Then
        assertThat(dto.getId(), equalTo(originWage.getId()));
        assertThat(dto.getEntryDate(), equalTo(originWage.getEntryDate()));
        assertThat(dto.getQuantity(), equalTo(originWage.getQuantity()));
        assertThat(dto.getPaymentMethod(), equalTo(originWage.getPaymentMethod()));
        assertThat(dto.getWorkStationDto().getId(), equalTo(originWage.getWorkStation().getId()));
    }

    @Test
    public void mapDtoToWage() {
        //Given
        //When
        Wage entity = orikaMapper.map(originWageDto, Wage.class);
        //Then
        assertThat(entity.getId(), equalTo(originWageDto.getId()));
        assertThat(entity.getEntryDate(), equalTo(originWageDto.getEntryDate()));
        assertThat(entity.getQuantity(), equalTo(originWageDto.getQuantity()));
        assertThat(entity.getPaymentMethod(), equalTo(originWageDto.getPaymentMethod()));
        assertThat(entity.getWorkStation().getId(), equalTo(originWageDto.getWorkStationDto().getId()));
    }

    @Test
    public void shouldFindWage() {
        //Given
        when(wageRepo.findOne(33L)).thenReturn(originWage);
        //When

        WageDto wageDto = employeeWageService.findWage(33L);

        //Then
        verify(orikaMapper).map(originWage, WageDto.class);
        assertThat(wageDto.getId(), equalTo(originWage.getId()));

    }

    @Test
    public void shouldSaveWage() {
        //Given
        when(wageRepo.save(originWage)).thenReturn(originWage);
        when(orikaMapper.map(originWageDto, Wage.class)).thenReturn(originWage);
        when(employeePaymentFormRepo.findOne(any(Specification.class))).thenReturn(new EmployeePaymentForm());

        //When
        WageDto wageDto = employeeWageService.save(42L, originWageDto);

        //Then
        verify(wageRepo).save(originWage);
        verify(orikaMapper).map(originWageDto, Wage.class);
        verify(orikaMapper).map(originWage, WageDto.class);
        assertThat(originWage.getPaymentMethod(), notNullValue());
        assertThat(wageDto.getId(), equalTo(originWage.getId()));

    }

    @Test
    public void shouldDeleteWage() {
        //Given
        when(wageRepo.findOne(33L)).thenReturn(originWage);
        //When

        employeeWageService.delete(33L);

        //Then
        verify(wageRepo).delete(33L);
    }

    @Test
    public void shouldUpdateWage() {
        //Given
        originWageDto.setQuantity(23f);
        Long wageId = originWageDto.getId();
        when(wageRepo.findOne(wageId)).thenReturn(originWage);
        //When
        WageDto wageDto = employeeWageService.update(wageId, originWageDto);
        //Then
        assertThat(wageDto.getQuantity(), equalTo(23f));
        verify(wageRepo).findOne(wageId);

    }

}
