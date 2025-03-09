package com.solusoftec.controllers.tiktok;

import com.solusoftec.models.tiktok.TiktokCredentials;
import com.solusoftec.services.tiktok.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/tiktok/credentials")
@CrossOrigin(origins = "http://localhost:3000")
public class CredentialsController {
    private final CredentialsService credentialService;

    @Autowired
    public CredentialsController(CredentialsService credentialService) {
        this.credentialService = credentialService;
    }

    // Endpoint para obtener todas las credenciales
    @GetMapping
    public List<TiktokCredentials> getAllCredentials() throws IOException {
        return credentialService.getCredentials();
    }

    // Endpoint para añadir una nueva credencial
    @PostMapping
    public void addCredential(@RequestParam String username, @RequestParam String password) throws IOException {
        credentialService.addCredential(username, password);
    }



}
