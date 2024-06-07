package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    @PostMapping("/emps")
    public Emp executePost(@RequestBody Emp queryRequest) throws IOException {
        return empService.save(queryRequest);

    }

    @GetMapping("/emps")
    public List<Emp> get() {
        return empService.getResumeFilePaths11();
    }


}
