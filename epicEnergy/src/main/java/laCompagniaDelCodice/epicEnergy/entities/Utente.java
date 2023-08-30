package laCompagniaDelCodice.epicEnergy.entities;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Utenti")
public class Utente implements UserDetails {

	@Id
	@GeneratedValue
	private UUID id;

	private String username;
	private String password;
	private String email;
	private String nome;
	private String cognome;

	@ManyToOne
	private Ruolo ruolo;

	public Utente(String username, String password, String email, String nome, String cognome, Ruolo ruolo) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.ruolo = ruolo;
	}

	/*
	 * DA VERIFICARE IL ruolo.toString. In caso dell'utilizzo di un Enum, lui
	 * prenderebbe ruolo.getName
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return List.of(new SimpleGrantedAuthority(ruolo.name()));
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public Utente(UUID id, String username, String password, String email, String nome, String cognome) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
	}

}
