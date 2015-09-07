package de.codenorm.certification;

import de.codenorm.certification.config.OrikaMapper;
import de.codenorm.certification.domain.WorkStation;
import de.codenorm.certification.domain.WorkingHour;
import org.junit.Test;
import rest.model.WorkingHoursDto;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by can on 17.08.15.
 */
public class OrkiaMapperTester {

    @Test
    public void shouldMapWorkingHourToDto() {
        //Given
        WorkingHour workingHour = new WorkingHour();
        workingHour.setId(22L);
        workingHour.setDate(new Date());
        WorkStation workStation = new WorkStation();
        workStation.setId(33L);
        workingHour.setWorkStation(workStation);
        //When
        WorkingHoursDto workingHoursDto = new OrikaMapper().map(workingHour, WorkingHoursDto.class);
        //Then
        assertThat(workingHoursDto, notNullValue());
        assertThat(workingHoursDto.getId(), equalTo(workingHour.getId()));
        assertThat(workingHoursDto.getEntryDate(), equalTo(workingHour.getDate()));
        assertThat(workingHoursDto.getWorkStationDto().getId(), equalTo(workingHour.getWorkStation().getId()));
    }
}
