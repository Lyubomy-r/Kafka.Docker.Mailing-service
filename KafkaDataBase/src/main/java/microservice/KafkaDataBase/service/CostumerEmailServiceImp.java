package microservice.KafkaDataBase.service;

import microservice.KafkaDataBase.dao.CostumerEmailRepository;
import microservice.KafkaDataBase.entity.CostumerEmailTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CostumerEmailServiceImp implements CostumerEmailService {

    private final CostumerEmailRepository costumerEmailRepository;

    @Override
    public List<CostumerEmailTable> findAll() {
        return costumerEmailRepository.findAll();
    }

    @Override
    public CostumerEmailTable save(CostumerEmailTable costumerEmail) {
        return costumerEmailRepository.save(costumerEmail);
    }
    @Override
    public List<String>  getAllEmails (){
        return costumerEmailRepository.getAllEmails();
    }
}
