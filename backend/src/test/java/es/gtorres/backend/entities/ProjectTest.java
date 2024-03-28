package es.gtorres.backend.entities;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProjectTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testWithBlankField() {
        List<String> skillList  = new ArrayList<>();
        skillList.add("Skill1");
        skillList.add("Skill2");

        List<String> imageList = new ArrayList<>();
        imageList.add("image1");
        imageList.add("image2");

        Project project1 = new Project("", "description", skillList, imageList);
        Project project2 = new Project("title", "", skillList, imageList);
        Project project3 = new Project("title", "description", new ArrayList<>(), imageList);
        Project project4 = new Project("title", "description", skillList, new ArrayList<>());

        assertFalse(validator.validate(project1).isEmpty());
        assertFalse(validator.validate(project2).isEmpty());
        assertFalse(validator.validate(project3).isEmpty());
        assertFalse(validator.validate(project4).isEmpty());
    }

    @Test
    public void testWithNullField() {
        List<String> skillList  = new ArrayList<>();
        skillList.add("Skill1");
        skillList.add("Skill2");

        List<String> imageList = new ArrayList<>();
        imageList.add("image1");
        imageList.add("image2");

        Project project1 = new Project(null, "description", skillList, imageList);
        Project project2 = new Project("title", null, skillList, imageList);
        Project project3 = new Project("title", "description", null, imageList);
        Project project4 = new Project("title", "description", skillList, null);

        assertFalse(validator.validate(project1).isEmpty());
        assertFalse(validator.validate(project2).isEmpty());
        assertFalse(validator.validate(project3).isEmpty());
        assertFalse(validator.validate(project4).isEmpty());
    }

    @Test
    public void testConstructorAndGetters() {
        String title = "title";
        String description = "description";

        List<String> skillList  = new ArrayList<>();
        skillList.add("Skill1");
        skillList.add("Skill2");

        List<String> imageList = new ArrayList<>();
        imageList.add("image1");
        imageList.add("image2");

        Project project = new Project(title, description, skillList, imageList);

        assertEquals(title, project.getTitle());
        assertEquals(description, project.getDescription());
        assertEquals(skillList, project.getSkill());
        assertEquals(imageList, project.getImagesList());
    }

    @Test
    public void testSetters() {
        String title = "title";
        String description = "description";

        List<String> skillList  = new ArrayList<>();
        skillList.add("Skill1");
        skillList.add("Skill2");

        List<String> imageList = new ArrayList<>();
        imageList.add("image1");
        imageList.add("image2");

        Project project = new Project();

        project.setTitle(title);
        project.setDescription(description);
        project.setSkill(skillList);
        project.setImagesList(imageList);

        assertEquals(title, project.getTitle());
        assertEquals(description, project.getDescription());
        assertEquals(skillList, project.getSkill());
        assertEquals(imageList, project.getImagesList());
    }
}
