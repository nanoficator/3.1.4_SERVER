package crud.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
@Entity
@Table (name = "authorities")
public class Authority implements GrantedAuthority {

    @Id
    @Column (name = "authority_id", updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (unique = true)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Authority objRole = (Authority) obj;
        if (this.id.equals(objRole.id) && this.name.equals(objRole.name)) {
            return true;
        }
        return false;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
