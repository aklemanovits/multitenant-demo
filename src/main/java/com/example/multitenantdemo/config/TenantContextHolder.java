package com.example.multitenantdemo.config;

import org.springframework.core.NamedInheritableThreadLocal;

public final class TenantContextHolder {

	private static final ThreadLocal<TenantContext> currentTenant = new NamedInheritableThreadLocal<>("TenantContext");

	private TenantContextHolder() {
	}

	public static TenantContext getCurrent() {
		return currentTenant.get();
	}

	public static void setCurrent(TenantContext context) {
		currentTenant.set(context);
	}

	public static void clearContext() {
		currentTenant.remove();
	}
}
