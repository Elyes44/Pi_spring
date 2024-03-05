package tn.esprit.pi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pi.Interfaces.IDiscussion;
import tn.esprit.pi.entities.Discussion;

import java.util.List;

@RestController
@RequestMapping("/Discussion")
@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600, allowCredentials="true")

public class DiscussionController {
    @Autowired
    IDiscussion iDiscussion;

    @PostMapping("/add/{idRec}/{idAdmin}")
public ResponseEntity<Discussion> createDiscussion(@RequestBody Discussion discussion , @PathVariable Long idRec, @PathVariable Long idAdmin){
        return iDiscussion.createDiscussion(discussion,idRec,idAdmin);
    }
    @GetMapping("/getAll/{id}")
    public ResponseEntity<List<Discussion>> createDiscussion(@PathVariable Long id){
        return iDiscussion.getAllByReclamation(id);
    }

}
