package sky.pro.SkyDreamTeam.AnimalService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class AnimalServiceApplication {

	public static void main(String[] args) {
		System.out.println("REER");
		SpringApplication.run(AnimalServiceApplication.class, args);
	}

}
