package tn.esprit.pi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String nom) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(nom)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + nom));

        return UserDetailsImpl.build(user);
    }

}
