package tn.esprit.pi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter

public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idApp;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyy-mm-dd")

    private Date dateApp;



    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern="hh:mm:ss")

    private Date heureDebut;

    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern="hh:mm:ss")

    private Date heureFin;


    @ManyToOne
    @JoinColumn(name="id_user")
   // @JsonBackReference

    private User user;




}
