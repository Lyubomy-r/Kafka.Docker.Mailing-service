package microservice.KafkaDataBase.service;

import microservice.KafkaDataBase.entity.CostumerEmailTable;

import java.util.List;

public interface CostumerEmailService {

   public List<CostumerEmailTable> findAll();

   public CostumerEmailTable save(CostumerEmailTable costumerEmail);
   public List<String>  getAllEmails ();

}
