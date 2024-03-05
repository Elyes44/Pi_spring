package tn.esprit.pi.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter

public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long idService;


    @Column(name ="NOM")
    private  String nom;

    @Column(name ="DESCRIPTION")
    private  String description;

    @OneToMany(mappedBy = "service")
    private Set<Chambre> Chambres;

}
