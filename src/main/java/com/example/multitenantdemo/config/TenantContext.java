package com.example.multitenantdemo.config;

import org.springframework.lang.Nullable;

public record TenantContext(@Nullable String id) {
}
