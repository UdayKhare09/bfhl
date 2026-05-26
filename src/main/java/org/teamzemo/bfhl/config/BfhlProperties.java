package org.teamzemo.bfhl.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bfhl")
public record BfhlProperties(
        String fullName,
        String dateOfBirth,
        String email,
        String rollNumber
) {

    public BfhlProperties {
        fullName = hasText(fullName) ? fullName : "john_doe";
        dateOfBirth = hasText(dateOfBirth) ? dateOfBirth : "17091999";
        email = hasText(email) ? email : "john@xyz.com";
        rollNumber = hasText(rollNumber) ? rollNumber : "ABCD123";
    }

    public String userId() {
        return fullName.toLowerCase().replaceAll("\\s+", "_") + "_" + dateOfBirth;
    }

    private static boolean hasText(String value) {
        return value != null && !value.isBlank();
    }
}
