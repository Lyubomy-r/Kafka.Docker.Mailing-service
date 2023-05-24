package microservice.KafkaDataBase.controller;

import microservice.KafkaDataBase.entity.CostumerEmailTable;
import microservice.KafkaDataBase.service.CostumerEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/emailList")
@RequiredArgsConstructor
public class CostumerEmailController {

        private final CostumerEmailService costumerEmailService;

        @GetMapping("/showAll")
        public List<CostumerEmailTable> showAllEmails(){
            return costumerEmailService.findAll();
        }

        @GetMapping("/showEmails")
        public List<String> showEmails(){
            return costumerEmailService.getAllEmails();
        }

        @PostMapping("/costumerEmail")
        public  CostumerEmailTable saveCostumerEmail(@RequestBody CostumerEmailTable costumerEmail){
            costumerEmailService.save(costumerEmail);
            return costumerEmail;
        }


}
