package com.solusoftec.services.facebook;


import com.solusoftec.models.facebook.FBCredentials;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class FBCredentialsService {


    private static final String CONFIG_FILE_PATH = "D:/Joeseep/web_scrap/Selenium_scrape/facebook_scrape/credentials.conf";

    // Método para leer credenciales
    public List<FBCredentials> getCredentials() throws IOException {
        List<FBCredentials> credentials = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE_PATH))) {
            String line;
            String username = null;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("#") || line.isEmpty()) {
                    continue; // Ignorar comentarios y líneas vacías
                }
                if (line.startsWith("usernamekey")) {
                    username = line.split("=")[1];
                } else if (line.startsWith("passwordkey") && username != null) {
                    String password = line.split("=")[1];
                    credentials.add(new FBCredentials(username, password));
                    username = null;
                }
            }
        }
        return credentials;
    }

    // Método para añadir credenciales
    public void addCredential(String username, String password) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_FILE_PATH, true))) {
            writer.write(String.format("usernamekey=%s%npasswordkey=%s%n", username, password));
        }
    }

}
