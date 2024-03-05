package tn.esprit.pi.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reclamation implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    Long idRec;

    @Column(name ="objet")
    String objet;

    @Column(name ="DESCRIPTION")
    String description;

    @Column(name ="status")
    @Enumerated(EnumType.STRING)
    Status status;


    @Column(name = "DATE" , columnDefinition = "TIMESTAMP")
    LocalDateTime date;

    @ManyToOne
    @JsonIgnore
    User user;


}
