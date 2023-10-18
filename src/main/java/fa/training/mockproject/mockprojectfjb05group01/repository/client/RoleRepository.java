package fa.training.mockproject.mockprojectfjb05group01.repository.client;

import fa.training.mockproject.mockprojectfjb05group01.entity.client.Role;
import fa.training.mockproject.mockprojectfjb05group01.model.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r.id FROM Role r  " +
            "WHERE r.roleType = :roleType")
    int findRoleIdByRoleType(@Param("roleType")String roleType);

    @Query("SELECT r FROM Role r " +
            "WHERE r.roleType = :roleName")
    Optional<Role> findRoleByRoleType(@Param("roleName")RoleType roleName);

    @Query("SELECT r.id FROM Role r " +
            "WHERE r.roleType = :roleType")
    int getRoleIdByRoleType(@Param("roleType")String roleType);

}
