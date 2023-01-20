package sky.pro.SkyDreamTeam.AnimalService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sky.pro.SkyDreamTeam.AnimalService.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
