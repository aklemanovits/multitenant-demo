package com.example.multitenantdemo.config;

import static org.apache.commons.logging.LogFactory.getLog;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class TenantIdRoutingDataSource extends AbstractRoutingDataSource {

	private static final Log log = getLog(TenantIdRoutingDataSource.class);

	protected final AtomicBoolean initialized = new AtomicBoolean();

	public TenantIdRoutingDataSource(Map<String, DataSource> targetDataSources) {
		super.setTargetDataSources(Map.copyOf(targetDataSources));
	}

	@Override
	protected DataSource determineTargetDataSource() {
		if ( this.initialized.compareAndSet(false, true) ) {
			this.afterPropertiesSet();
		}

		return super.determineTargetDataSource();
	}


	@Override
	protected Object determineCurrentLookupKey() {
		TenantContext tenantContext = TenantContextHolder.getCurrent();

		if ( tenantContext != null ) {
			return tenantContext.id();
		} else {
			log.warn("Can't determine lookup key, TenantContext is empty.");

			return null;
		}
	}
}

