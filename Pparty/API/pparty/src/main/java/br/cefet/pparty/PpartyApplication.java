package br.cefet.pparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
public class PpartyApplication {

	public static void main(String[] args) {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setUrl("jdbc:mysql://localhost:3306/SistemaParty");
		SpringApplication.run(PpartyApplication.class, args);
	}

}
