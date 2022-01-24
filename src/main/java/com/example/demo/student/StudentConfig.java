package com.example.demo.student;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig 
{
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository)
	{
		return args ->
		{
			Student kushal = new Student
					(
						"Kushal", "ktmg@gmail.com", LocalDate.of(1996, 11, 12)
					);
			
			Student kpoli = new Student
					(
						"KPOli", "kpoli@gmail.com", LocalDate.of(1969, 01, 11)
					);
			
			repository.saveAll // save the students as a list inside our database.
			(
				List.of(kushal,kpoli)
			);
		};
	}
}
