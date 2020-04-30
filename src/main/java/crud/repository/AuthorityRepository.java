package crud.repository;

import crud.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    @Query("SELECT u FROM Authority u WHERE u.name = :name")
    public Authority findRoleByName (@Param("name") String name);

}
