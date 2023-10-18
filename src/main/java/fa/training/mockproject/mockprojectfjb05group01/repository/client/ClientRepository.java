package fa.training.mockproject.mockprojectfjb05group01.repository.client;

import fa.training.mockproject.mockprojectfjb05group01.entity.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long>{
    @Query("SELECT c FROM Client c " +
            "WHERE c.email = :email")
    Optional<Client> findClientByEmail(@Param("email") String email);

    @Query("SELECT (count(c) > 0) FROM Client c " +
            "WHERE c.email = :email")
    boolean existsClientByEmail(@Param("email") String email);

    @Query("SELECT (count(c) > 0) FROM Client c " +
            "WHERE c.phone = :phone")
    boolean existsClientByPhone(@Param("phone") String phone);

}
