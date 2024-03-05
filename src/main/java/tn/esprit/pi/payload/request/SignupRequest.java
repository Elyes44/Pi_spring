package tn.esprit.pi.payload.request;



import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String nom;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  private Set<String> role;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;





  @NotBlank
  @Size(max = 20)
  private String prenom;


  private String numero;

  @Temporal(TemporalType.DATE)
  @JsonFormat(pattern="yyyy-MM-dd")
  private Date birthday;


  private String address;


  private int age;


  private String cin;

  private String specialite;
  @NotBlank
  String username;




}
