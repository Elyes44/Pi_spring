package tn.esprit.pi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pi.Interfaces.Ireclamation;
import tn.esprit.pi.entities.Reclamation;
import tn.esprit.pi.entities.Status;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reclamation")
@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600, allowCredentials="true")
public class ReclamationController {
    @Autowired
    Ireclamation ireclamation;
    @PostMapping("/createReclamation/{id}")
    public ResponseEntity<Reclamation> createReclamation (@RequestBody Reclamation reclamation, @PathVariable Long id)
    {
        return ireclamation.createReclamation(reclamation, id);
    }
    @DeleteMapping ("/deleteReclamation/{id}")
    public ResponseEntity <HttpStatus> deleteReclamation (@PathVariable Long id)
    {
        return ireclamation.deleteReclamation(id);
    }
    @PutMapping ("/updateReclamation/{id}")
    public ResponseEntity <Reclamation> UpdateReclamation (@PathVariable("id") Long id,@RequestBody Reclamation reclamation)
    {
        return ireclamation.updateReclamation(id, reclamation);
    }

    @GetMapping("/displayreclamation")
    public ResponseEntity <List<Reclamation>> DisplayReclamation()
    {
        return ireclamation.DisplayReclamation();
    }
    @GetMapping("/displayreclamation/{id}")
    public ResponseEntity <Reclamation> DisplayReclamationByID(@PathVariable Long id)
    {
        return ireclamation.DisplayReclamationByID(id);
    }
    @PutMapping("/ended/{id}")
    public ResponseEntity<Reclamation> endDisc(@PathVariable Long id){
        return ireclamation.endDiscussion(id);
    }

    @GetMapping("/statistiques")

    public ResponseEntity<Map<String,Object>> Statistique() {
        Map<String, Object> response = new HashMap<>();
        response.put("Nombre des reclamations traitées ", ireclamation.countAllByStatus(Status.traitée));
        response.put("Nombre des reclamations en cours de traitement", ireclamation.countAllByStatus(Status.encours));
        response.put("Nombre des reclamations non traitées", ireclamation.countAllByStatus(Status.non_traitée));
        return new ResponseEntity<>(response,HttpStatus.OK);}





    }
