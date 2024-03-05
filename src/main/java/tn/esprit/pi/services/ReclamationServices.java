package tn.esprit.pi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esprit.pi.Interfaces.Ireclamation;
import tn.esprit.pi.entities.Discussion;
import tn.esprit.pi.entities.Reclamation;
import tn.esprit.pi.entities.Status;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.DiscussionRepo;
import tn.esprit.pi.repositories.ReclamationRepo;
import tn.esprit.pi.repositories.UserRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReclamationServices implements Ireclamation {
    @Autowired
    private EmailSenderService senderService;
    @Autowired
    private DiscussionRepo discussionRepo;
    @Autowired
    ReclamationRepo reclamationRepo;
    @Autowired
    UserRepo userRepo;
    @Override
    public ResponseEntity<Reclamation> createReclamation(Reclamation reclamation , Long id) {

        try{
            User user = userRepo.findById(id).orElse(null);
            reclamation.setStatus(Status.non_traitée);
            reclamation.setDate(LocalDateTime.now());
            reclamation.setUser(user);
           Reclamation rec1 = reclamationRepo.save(reclamation);

            Discussion discussion = new Discussion();
            discussion.setReclamation(rec1);
            discussion.setMsg(rec1.getDescription());
            discussion.setUser(userRepo.findById(rec1.getUser().getIdUser()).orElse(null));
            discussion.setDate(LocalDateTime.now());
            discussionRepo.save(discussion);

            return new ResponseEntity<>(reclamation, HttpStatus.CREATED);
        }
        catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<HttpStatus> deleteReclamation(Long id) {
       try{
           reclamationRepo.deleteById(id);
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }catch (Exception exception){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);       }

    }

    @Override
    public ResponseEntity<Reclamation> updateReclamation(Long id, Reclamation reclamation) {
        try {
            Optional<Reclamation>  rec = reclamationRepo.findById(id);
            if (rec.isPresent()) {
                Reclamation reclam = rec.get();
                reclam.setStatus(Status.non_traitée);
                reclam.setDate(LocalDateTime.now());
                reclam.setObjet(reclamation.getObjet());
                reclam.setDescription(reclamation.getDescription());
                reclamationRepo.save(reclam);
                return new ResponseEntity<>(reclam, HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Reclamation>> DisplayReclamation() {
try{
    List<Reclamation> list =reclamationRepo.findAll();
    if(list.isEmpty())
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    else
        return new ResponseEntity<>(list,HttpStatus.OK);
}
catch (Exception exception) {
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
}
    }

    @Override
    public ResponseEntity <Reclamation> DisplayReclamationByID(Long id) {
        try{
             Optional<Reclamation> rec =reclamationRepo.findById(id);
            if(rec.isPresent()){
                Reclamation reclam = rec.get();
                return new ResponseEntity<>(reclam,HttpStatus.OK);  }
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Reclamation> endDiscussion(Long id) {
        try{
            Reclamation reclamation =reclamationRepo.findById(id).orElse(null);
            reclamation.setStatus(Status.traitée);
            reclamationRepo.save(reclamation);
            String email = reclamation.getUser().getAddress();
            senderService.sendSimpleEmail(email,
                    "Votre reclamatioin a été traitéé avec Succées!",
                    "Reclamation"
                    );

            return new ResponseEntity<>(reclamation,HttpStatus.OK);
        }
        catch(Exception e){return new ResponseEntity<>(HttpStatus.NOT_FOUND);}

    }

    @Override
    public ResponseEntity<Integer> countAllByStatus(Status status) {

        try {
            int i= reclamationRepo.countAllByStatus(status);
            return new ResponseEntity<>(i,HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}
