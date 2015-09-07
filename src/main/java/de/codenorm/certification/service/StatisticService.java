package de.codenorm.certification.service;

import de.codenorm.certification.repository.EmployeeRepo;
import de.codenorm.certification.repository.WorkStationRepo;
import de.codenorm.certification.repository.WorkingHourRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.model.DashboardDto;


@Service
public class StatisticService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private WorkStationRepo workStationRepo;

    @Autowired
    private WorkingHourRepo workingHourRepo;

    public DashboardDto find() {
        DashboardDto dashboardDto = new DashboardDto();
        dashboardDto.setCountEmployee((int) employeeRepo.count());
        dashboardDto.setCountWorkStations((int) workStationRepo.count());
        dashboardDto.setCountEmployeeCertifications(employeeRepo.countEmployeeWorkStation().intValue());
        int countAllHours = workingHourRepo.countAllHours();
        dashboardDto.setCountWorkingHours(countAllHours);
        return dashboardDto;
    }
}
