package tn.esprit.pi.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private String password;
  private Long id;
  private String nom;
  private String email;
  private List<String> roles;

  public JwtResponse(String accessToken, Long id, String nom, String email, List<String> roles) {
    this.token = accessToken;
    this.id = id;
    this.nom = nom;
    this.email = email;
    this.roles = roles;
  }




}
