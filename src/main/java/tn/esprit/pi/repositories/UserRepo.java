package tn.esprit.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pi.entities.User;

public interface UserRepo  extends JpaRepository<User, Long> {
}
