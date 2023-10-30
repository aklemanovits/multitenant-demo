package com.example.multitenantdemo;

import java.util.StringJoiner;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MultiTenantDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiTenantDemoApplication.class, args);
	}

}

interface CustomerRepository extends JpaRepository<Customer, Long> {

}

@Entity
class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = "SEQ", allocationSize = 50)
	private Long id;

	private String name;

	public Customer() {
	}

	public Customer(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ")
				.add("id=" + id)
				.add("name='" + name + "'")
				.toString();
	}
}
