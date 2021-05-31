package omds;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import omds.data.MedicationRepository;

@SpringBootApplication
public class OmdCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmdCloudApplication.class, args);
	}

	//@Bean
	public CommandLineRunner dataLoader(MedicationRepository repo){
		return new CommandLineRunner(){
			@Override
			public void run(String... args) throws Exception{
				//List<Medication> medications = new ArrayList<>();
        		repo.findAll().forEach(i -> repo.save(i));
			}
		};
	}
}
