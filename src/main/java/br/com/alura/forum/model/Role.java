package br.com.alura.forum.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Role implements GrantedAuthority {

    public static final	Role ROLE_ADMIN	= new Role("ROLE_ADMIN");
    public static final	Role ROLE_ALUNO	= new Role("ROLE_ALUNO");

    @Id
    private	String authority;

}
