package tn.esprit.pi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user")

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long idUser;
    @NotBlank
    @Size(max = 20)
    @Column(name = "NOM")
    private String nom;
    @NotBlank
    @Size(max = 20)
    @Column(name = "PRENOM")
    private String prenom;

    @Column(nullable=false, unique=true)
    private String email;

    @NotBlank
    @JsonIgnore
    private String password;

    @Column(name = "NUMERO")
    private String numero;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "BIRTHDAY")
    private Date birthday;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "AGE")
    private int age;

    @Column(name = "CIN")
    private String cin;


    @Column(nullable= true ,name = "SEPECIALITE")
    private String specialite;
    @NotBlank
    String username;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @OneToMany(mappedBy="user")
    private Set<Payment> Payments;

    @OneToMany(mappedBy="user")
    private Set<Appointment> Appointments;


    @OneToOne(mappedBy = "user")
    private Chambre chambre;

    @OneToMany(mappedBy = "user")
    private  Set<Reclamation> Reclamations;


    @ManyToOne
    private Pharmacie pharmacie;


    @OneToOne(cascade = CascadeType.REMOVE)
    private DossierMedical dossierMedical;


    @OneToMany(cascade = CascadeType.REMOVE ,mappedBy = "user")
    private Set<Prescription> Prescriptions;



    public User(String nom, String email, String cin, String numero, String address, String prenom, int age, Date birthday,String username, String password) {
        super();
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.address= address;
        this.age=age ;
        this.numero = numero;
        this.cin=cin ;
        this.prenom= prenom ;
        this.birthday=birthday;
        this.username=username;
    }
}
