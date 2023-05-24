package microservice.KafkaConsumerDocker.controller;

import microservice.KafkaConsumerDocker.fiegnService.FeignServiceUtil;
import microservice.KafkaConsumerDocker.model.CostumerEmailTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/allEmail")
public class FeignController {

    private final FeignServiceUtil feignServiceUtil;

    @GetMapping("/showAll")
    public List<CostumerEmailTable> showAllEmails() throws Exception {

        try {

            return feignServiceUtil.showAllEmails();


        }catch (Exception e) {
            throw  new Exception (e.getMessage());
        }

    }

    @GetMapping("/showEmails")
    public List<String> showEmails() throws Exception {
        try {

            return feignServiceUtil.showEmails();


        }catch (Exception e) {
            throw  new Exception (e.getMessage());
        }
    }

}
