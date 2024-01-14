package repository;

import model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnimalRepository extends JpaRepository<Animal, Long>{

}
