package es.gtorres.backend.controllers;

import es.gtorres.backend.entities.Skill;
import es.gtorres.backend.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/skill")
public class SkillController {

    private final SkillRepository skillRepository;

    @Autowired
    public SkillController(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @GetMapping("/findAll")
    public List<Skill> findAll() {
        return  skillRepository.findAll();
    }
}
