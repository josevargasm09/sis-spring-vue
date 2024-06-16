// src/main/java/com/bezkoder/spring/security/jwt/payload/request/RoleRequest.java
package com.bezkoder.spring.security.jwt.payload.request;

import javax.validation.constraints.NotBlank;

public class RoleRequest {
    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
