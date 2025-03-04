package com.solusoftec.models.twitter;

import lombok.Data;

@Data
public class XCredentials {
    private String usernameKey;
    private String passwordKey;

    // Constructor con todos los argumentos (opcional si necesitas instanciarlo manualmente)
    public XCredentials(String usernameKey, String passwordKey) {
        this.usernameKey = usernameKey;
        this.passwordKey = passwordKey;
    }
}
