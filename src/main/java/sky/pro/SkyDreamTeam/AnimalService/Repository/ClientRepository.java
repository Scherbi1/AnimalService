package sky.pro.SkyDreamTeam.AnimalService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sky.pro.SkyDreamTeam.AnimalService.Model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}