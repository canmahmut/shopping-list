package de.codenorm.certification.config;

import de.codenorm.certification.domain.*;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import rest.model.*;

/**
 * Created by can on 26.05.15.
 */
@Component
public class OrikaMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        super.configure(factory);
        factory.registerClassMap(factory.classMap(Wage.class, WageDto.class)
                .byDefault()
                .toClassMap());
        factory.registerClassMap(factory.classMap(WageDto.class, Wage.class)
                .byDefault()
                        // .field("employeePaymentFormDto", "employeePaymentForm")
                .field("workStationDto", "workStation")
                .toClassMap());

        factory.registerClassMap(factory.classMap(WorkStation.class, WorkStationDto.class).byDefault().toClassMap());
        factory.registerClassMap(factory.classMap(WorkStationDto.class, WorkStation.class).byDefault().toClassMap());

        factory.registerClassMap(factory.classMap(EmployeePaymentForm.class, EmployeePaymentFormDto.class).byDefault().toClassMap());
        factory.registerClassMap(factory.classMap(EmployeePaymentFormDto.class, EmployeePaymentForm.class).byDefault().toClassMap());

        factory.registerClassMap(factory.classMap(EmployeeDto.class, Employee.class).byDefault().toClassMap());
        factory.registerClassMap(factory.classMap(Employee.class, EmployeeDto.class).byDefault().toClassMap());


        factory.registerClassMap(factory.classMap(CertificateDto.class, Certificate.class).byDefault().toClassMap());
        factory.registerClassMap(factory.classMap(Certificate.class, CertificateDto.class).byDefault().toClassMap());



        factory.registerClassMap(factory.classMap(WorkingHoursDto.class, WorkingHour.class).byDefault()
                .field("entryDate", "date")
                .field("workStationDto", "workStation")
                .field("employeeDto", "employee")
                .toClassMap());
        factory.registerClassMap(factory.classMap(WorkingHour.class, WorkingHoursDto.class).byDefault()
                .field("date", "entryDate")
                .field("workStation", "workStationDto")
                .field("employee", "employeeDto")
                .toClassMap());



    }
}
