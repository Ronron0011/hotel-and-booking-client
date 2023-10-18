package fa.training.mockproject.mockprojectfjb05group01.repository.system;

import fa.training.mockproject.mockprojectfjb05group01.entity.system.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
