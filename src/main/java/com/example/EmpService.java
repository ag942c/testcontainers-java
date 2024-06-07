package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmpService {

    @Autowired
    private EmpRepository empRepo;


    public Emp save(Emp resume) {
        return empRepo.save(resume);
    }


    public List<Emp> getResumeFilePaths11() {
        return empRepo.findAll();


    }


}



