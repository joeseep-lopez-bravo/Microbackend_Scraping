package com.solusoftec.models.instagram;

import lombok.Data;

@Data
public class IGCredentials {
    private String usernameKey;
    private String passwordKey;

    // Constructor con todos los argumentos (opcional si necesitas instanciarlo manualmente)
    public IGCredentials(String usernameKey, String passwordKey) {
        this.usernameKey = usernameKey;
        this.passwordKey = passwordKey;
    }
}
