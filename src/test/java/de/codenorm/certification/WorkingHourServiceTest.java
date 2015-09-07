package de.codenorm.certification;

import de.codenorm.certification.service.WorkingHourService;
import de.codenorm.certification.sheet.User;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

/**
 * Created by can on 15.08.15.
 */
public class WorkingHourServiceTest {


    private WorkingHourService workingHourService = new WorkingHourService();


    @Test
    public void foo() throws IOException {
        List<User> user = workingHourService.createUser(new ClassPathResource("data.xlsx").getInputStream());
        System.out.println("user.get(6) = " + user.get(6));
    }

}
