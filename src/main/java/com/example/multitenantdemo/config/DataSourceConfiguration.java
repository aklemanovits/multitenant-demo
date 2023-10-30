package com.example.multitenantdemo.config;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfiguration {

	@Bean
	public DataSource ds1() {
		DataSourceProperties properties = new DataSourceProperties();
		properties.setUrl("jdbc:h2:mem:ds1;Mode=Oracle");

		return properties.initializeDataSourceBuilder().build();
	}

	@Bean
	public DataSource ds2() {
		DataSourceProperties properties = new DataSourceProperties();
		properties.setUrl("jdbc:h2:mem:ds2;Mode=Oracle");

		return properties.initializeDataSourceBuilder().build();
	}


	@Bean
	@Primary
	public TenantIdRoutingDataSource datasource(Map<String, DataSource> datasources) {
		datasources.remove("datasource");

		return new TenantIdRoutingDataSource(datasources);
	}
}
