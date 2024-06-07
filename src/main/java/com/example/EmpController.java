package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
public class EmpController {

    @Autowired
    private EmpService empService;

    @PostMapping("/emps")
    public Emp executePost(@RequestBody Emp queryRequest) throws IOException {
        log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~EMP Going to be saved " + queryRequest.toString());
        return empService.save(queryRequest);

    }

    @GetMapping("/emps")
    public List<Emp> get() {
        List<Emp> aaaaa = empService.getResumeFilePaths11();
        log.info("...............................................no of records " + aaaaa.size());
        return aaaaa;
    }


}
