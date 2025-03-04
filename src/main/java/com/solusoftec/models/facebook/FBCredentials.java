package com.solusoftec.models.facebook;

import lombok.Data;

@Data
public class FBCredentials{
    private String usernameKey;
    private String passwordKey;

    // Constructor con todos los argumentos (opcional si necesitas instanciarlo manualmente)
    public FBCredentials(String usernameKey, String passwordKey) {
        this.usernameKey = usernameKey;
        this.passwordKey = passwordKey;
    }
}
