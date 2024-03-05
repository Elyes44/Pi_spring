package tn.esprit.pi.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter

public class Pharmacie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long idPhar;


    @Column(name ="NOM")
    private  String nom;


    @OneToMany(mappedBy = "pharmacie")
    private Set<User> Users;

    @OneToMany(mappedBy = "pharmacie")
    private Set<Medicament> Medicaments;

}
