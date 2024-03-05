package tn.esprit.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pi.entities.Discussion;
import tn.esprit.pi.entities.Reclamation;

import java.util.List;
@Repository
public interface DiscussionRepo extends JpaRepository<Discussion,Long> {
    List<Discussion> findReclamationOrderByDate(Reclamation reclamation);
    List<Discussion> findAllByReclamationOrderByDate(Reclamation reclamation);
}
