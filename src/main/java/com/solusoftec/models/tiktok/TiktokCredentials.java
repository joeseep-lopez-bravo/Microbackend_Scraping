package com.solusoftec.models.tiktok;

import lombok.Data;

@Data
public class TiktokCredentials {
    private String usernameKey;
    private String passwordKey;

    // Constructor con todos los argumentos (opcional si necesitas instanciarlo manualmente)
    public TiktokCredentials(String usernameKey, String passwordKey) {
        this.usernameKey = usernameKey;
        this.passwordKey = passwordKey;
    }
}
