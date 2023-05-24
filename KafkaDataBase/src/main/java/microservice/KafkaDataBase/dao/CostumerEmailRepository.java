package microservice.KafkaDataBase.dao;

import microservice.KafkaDataBase.entity.CostumerEmailTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostumerEmailRepository  extends JpaRepository<CostumerEmailTable,Long> {

    @Query(" SELECT costumer.email FROM CostumerEmailTable costumer ")
    List<String>  getAllEmails ();

}
