package owpk;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import owpk.services.CitySearchingService;

@SpringBootApplication
@AllArgsConstructor
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    private final CitySearchingService citySearchingService;

    @Override
    public void run(String... args) throws Exception {
        citySearchingService.search("", 50f, -120f, 10);
    }
}