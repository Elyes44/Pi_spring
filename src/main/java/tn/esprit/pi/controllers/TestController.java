package tn.esprit.pi.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('MEDECIN') or hasRole('GESTIONNAIRE') or hasRole('ADMIN')")
    public String userAccess() {
        return "PASTIENT Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MEDECIN')")
    public String mEDAccess() {
        return "Moderator Board.";
    }
    @GetMapping("/GESTIONNAIRE")
    @PreAuthorize("hasRole('GESTIONNAIRE')")
    public String GESTIONNAIREAccess() {
        return "Moderator Board.";
    }
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}