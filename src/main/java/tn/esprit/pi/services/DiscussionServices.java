package tn.esprit.pi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esprit.pi.Interfaces.IDiscussion;
import tn.esprit.pi.entities.Discussion;
import tn.esprit.pi.entities.Reclamation;
import tn.esprit.pi.entities.Status;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.DiscussionRepo;
import tn.esprit.pi.repositories.ReclamationRepo;
import tn.esprit.pi.repositories.UserRepo;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class DiscussionServices implements IDiscussion {
    @Autowired
    DiscussionRepo discussionRepo;
    @Autowired
    ReclamationRepo reclamationRepo;
    @Autowired
    UserRepo userRepo;
    @Override
    public ResponseEntity<Discussion> createDiscussion(Discussion discussion, Long idRec ,Long idAdmin) {
        try{
            Reclamation reclamation = reclamationRepo.findById(idRec).orElse(null);
            if(reclamation.getStatus().toString().equals("traitée")){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            User user = userRepo.findById(idAdmin).orElse(null);
            discussion.setUser(user);
            discussion.setReclamation(reclamation);
            discussion.setDate(LocalDateTime.now());
            reclamation.setStatus(Status.encours);
            reclamationRepo.save(reclamation);
            discussionRepo.save(discussion);
            return new ResponseEntity<>(discussion,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);}

    }

    @Override
    public ResponseEntity<List<Discussion>> getAllByReclamation(Long id) {
        try{
            Reclamation reclamation = reclamationRepo.findById(id).orElse(null);
            List<Discussion> discussions =discussionRepo.findAllByReclamationOrderByDate(reclamation);
            return new ResponseEntity<>(discussions,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }
}
