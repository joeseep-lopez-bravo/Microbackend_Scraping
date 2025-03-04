package com.solusoftec.controllers.tiktok;

import com.solusoftec.models.facebook.FBCredentials;
import com.solusoftec.services.facebook.FBCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/tiktok/credentials")
@CrossOrigin(origins = "http://localhost:3000")
public class CredentialsController {
    private final FBCredentialsService credentialService;

    @Autowired
    public CredentialsController(FBCredentialsService credentialService) {
        this.credentialService = credentialService;
    }

    // Endpoint para obtener todas las credenciales
    @GetMapping
    public List<FBCredentials> getAllCredentials() throws IOException {
        return credentialService.getCredentials();
    }

    // Endpoint para añadir una nueva credencial
    @PostMapping
    public void addCredential(@RequestParam String username, @RequestParam String password) throws IOException {
        credentialService.addCredential(username, password);
    }



}
