package tn.esprit.pi.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Chambre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long idChambre;


    @Column(name ="NUMERO")
    private String num;


    @Column(name ="ETAGE")
    private int etage;


    @Column(name ="STATUS")
    private String status;

    @OneToOne
    private User user;


    @ManyToOne
    private Service service;
}
