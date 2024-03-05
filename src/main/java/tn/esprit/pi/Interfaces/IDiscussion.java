package tn.esprit.pi.Interfaces;

import org.springframework.http.ResponseEntity;
import tn.esprit.pi.entities.Discussion;

import java.util.List;

public interface IDiscussion {
    ResponseEntity<Discussion> createDiscussion(Discussion discussion, Long idRec ,Long idAdmin);
    ResponseEntity<List<Discussion>> getAllByReclamation(Long id);
}
