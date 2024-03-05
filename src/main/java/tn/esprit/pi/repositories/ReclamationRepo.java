package tn.esprit.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pi.entities.Reclamation;
import tn.esprit.pi.entities.Status;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Repository
public interface ReclamationRepo extends JpaRepository<Reclamation , Long> {

    int countAllByStatus( Status status);


}
