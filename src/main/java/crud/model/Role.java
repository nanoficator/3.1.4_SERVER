package crud.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "roles")
public class Role {

    @Id
    @Column (name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    @ManyToMany (mappedBy = "roles", fetch = FetchType.EAGER)
    private List<User> users;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String roleName) {
        this.name = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Role objRole = (Role) obj;
        if (this.id.equals(objRole.id) && this.name.equals(objRole.name)) {
            return true;
        }
        return false;
    }
}
