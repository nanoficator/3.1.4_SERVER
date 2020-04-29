package crud.repository;

import crud.model.Role;
import crud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT u FROM Role u WHERE u.name = :name")
    public Role findRoleByName (@Param("name") String name);

}
