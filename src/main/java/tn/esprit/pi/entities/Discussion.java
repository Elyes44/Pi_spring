package tn.esprit.pi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Discussion")
@ToString
public class Discussion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idDisc")
    Long idDisc;
    @Column
    String msg;

    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime date ;

    @ManyToOne
    User user;

    @ManyToOne
    Reclamation reclamation;
}
