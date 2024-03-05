package tn.esprit.pi.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class DossierMedical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_DOSS")
    private Long idDoss;


    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyy-mm-dd")
    @Column(name = "DATE")
    private Date date;

    @Column(name ="DESCRIPTION")
    private String description;


    @Column(name ="MALADIE")
    private String maladie;


    @Column(name ="ALLERGIE")
    private String allergie;

    @OneToOne
    private User user;





}
