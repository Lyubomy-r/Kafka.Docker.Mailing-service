package microservice.KafkaConsumerDocker.fiegnService;


import microservice.KafkaConsumerDocker.model.CostumerEmailTable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "feignDemo", url = "http://localhost:8082/emailList")
public interface FeignServiceUtil {

//    @GetMapping("/showAll")
//    List<Object> showAllEmails();

    @GetMapping("/showAll")
    List<CostumerEmailTable> showAllEmails();

    @GetMapping("/showEmails")
    List<String> showEmails();

//    @PostMapping("/costumerEmail")
//    Object saveCostumerEmail(@RequestBody Object costumerEmail);




}
