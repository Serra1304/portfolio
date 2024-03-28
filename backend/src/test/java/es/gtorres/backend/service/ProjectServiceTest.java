package es.gtorres.backend.service;

import es.gtorres.backend.entities.Project;
import es.gtorres.backend.repositories.ProjectRepository;
import es.gtorres.backend.services.ProjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @Test
    public void testAddProject_Success() {
        Project project = new Project();
        when(projectRepository.save(any())).thenReturn(project);

        ResponseEntity<String> response = projectService.addProject(project);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    public void testAddProject_Failure() {
        when(projectRepository.save(any())).thenThrow(new DataIntegrityViolationException("Error al guardar el proyecto"));

        ResponseEntity<String> response = projectService.addProject(new Project());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(projectRepository, times(1)).save(any());
    }

    @Test
    public void testFindAllProjects_NoContent() {
        when(projectRepository.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<?> response = projectService.findAllProject();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    public void testFindAllProjects_Success() {
        when(projectRepository.findAll()).thenReturn(projectList());

        ResponseEntity<?> response = projectService.findAllProject();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    public void testFindAllProjects_Failure() {
        when(projectRepository.findAll()).thenThrow(new DataIntegrityViolationException("Error al obtener la lista de proyectos"));

        ResponseEntity<?> response = projectService.findAllProject();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    public void testFindProjectById_Success() {
        Project project = new Project();
        when(projectRepository.findById(any())).thenReturn(Optional.of(project));

        ResponseEntity<?> response = projectService.findProjectById("123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(project, response.getBody());
        verify(projectRepository, times(1)).findById("123");
    }

    @Test
    public void testFindProjectById_BlankFieldError() {
        String id = "";
        ResponseEntity<?> response = projectService.findProjectById(id);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(projectRepository, times(0)).findById(id);
    }

    @Test
    public void testFindProjectById_NullFieldError() {
        ResponseEntity<?> response = projectService.findProjectById(null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(projectRepository, times(0)).findById(null);
    }

    @Test void testFindProjectById_InvalidFieldError() {
        String id = "Id_no_existe";
        ResponseEntity<?> response = projectService.findProjectById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(projectRepository, times(1)).findById(id);
    }

    @Test
    public void testFindProjectsById_Failure() {
        String id = "123";
        when(projectRepository.findById(id)).thenThrow(new DataIntegrityViolationException("Error al obtener el proyecto indicado"));

        ResponseEntity<?> response = projectService.findProjectById(id);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(projectRepository, times(1)).findById(id);
    }

    @Test
    public void testUpdateProjectById_SuccessWhitTitle() {
        Project project = new Project();
        Map<String, Object> data = new HashMap<>();
        data.put("title", "Updated Title");

        when(projectRepository.findById(any())).thenReturn(Optional.of(project));
        when(projectRepository.save(any())).thenReturn(project);

        ResponseEntity<?> response = projectService.updateProjectById("123", data);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Proyecto actualizado correctamente.", response.getBody());
        assertEquals("Updated Title", project.getTitle());
        verify(projectRepository, times(1)).findById("123");
        verify(projectRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateProjectById_SuccessWhitDescription() {
        Project project = new Project();
        Map<String, Object> data = new HashMap<>();
        data.put("description", "Updated Description");

        when(projectRepository.findById(any())).thenReturn(Optional.of(project));
        when(projectRepository.save(any())).thenReturn(project);

        ResponseEntity<?> response = projectService.updateProjectById("123", data);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Proyecto actualizado correctamente.", response.getBody());
        assertEquals("Updated Description", project.getDescription());
        verify(projectRepository, times(1)).findById("123");
        verify(projectRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateProjectById_SuccessWhitSkillList() {
        Project project = new Project();
        Map<String, Object> data = new HashMap<>();
        List<String> skillList  = new ArrayList<>();
        skillList.add("java");
        skillList.add("spring");

        data.put("skill", skillList);

        when(projectRepository.findById(any())).thenReturn(Optional.of(project));
        when(projectRepository.save(any())).thenReturn(project);

        ResponseEntity<?> response = projectService.updateProjectById("123", data);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Proyecto actualizado correctamente.", response.getBody());
        assertEquals(skillList, project.getSkill());
        verify(projectRepository, times(1)).findById("123");
        verify(projectRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateProjectById_SuccessWhitImagesList() {
        Project project = new Project();
        Map<String, Object> data = new HashMap<>();
        List<String> imageList = new ArrayList<>();
        imageList.add("url_image1");
        imageList.add("url_image2");

        data.put("imagesList", imageList);

        when(projectRepository.findById(any())).thenReturn(Optional.of(project));
        when(projectRepository.save(any())).thenReturn(project);

        ResponseEntity<?> response = projectService.updateProjectById("123", data);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Proyecto actualizado correctamente.", response.getBody());
        assertEquals(imageList, project.getImagesList());
        verify(projectRepository, times(1)).findById("123");
        verify(projectRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateProjectById_SuccessFields() {
        Project project = new Project();
        Map<String, Object> data = new HashMap<>();

        List<String> skillList  = new ArrayList<>();
        skillList.add("java");
        skillList.add("spring");

        List<String> imageList = new ArrayList<>();
        imageList.add("url_image1");
        imageList.add("url_image2");

        data.put("title", "Updated title");
        data.put("description", "Updated description");
        data.put("skill", skillList);
        data.put("imagesList", imageList);

        when(projectRepository.findById(any())).thenReturn(Optional.of(project));
        when(projectRepository.save(any())).thenReturn(project);

        ResponseEntity<?> response = projectService.updateProjectById("123", data);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Proyecto actualizado correctamente.", response.getBody());
        assertEquals("Updated title", project.getTitle());
        assertEquals("Updated description", project.getDescription());
        assertEquals(skillList, project.getSkill());
        assertEquals(imageList, project.getImagesList());
        verify(projectRepository, times(1)).findById("123");
        verify(projectRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateProjectById_InvalidField() {
        Project project = new Project();
        Map<String, Object> data = new HashMap<>();

        List<String> skillList  = new ArrayList<>();
        skillList.add("java");
        skillList.add("spring");

        List<String> imageList = new ArrayList<>();
        imageList.add("url_image1");
        imageList.add("url_image2");

        data.put("title", "Updated title");
        data.put("invalidField", "Updated description");
        data.put("skillList", skillList);
        data.put("imageList", imageList);

        when(projectRepository.findById(any())).thenReturn(Optional.of(project));

        ResponseEntity<?> response = projectService.updateProjectById("123", data);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(projectRepository, times(1)).findById("123");
        verify(projectRepository, times(0)).save(any());
    }

    @Test
    public void testUpdateCertificateById_BlankIdError() {
        String id = "";
        Map<String, Object> data = new HashMap<>();
        data.put("description", "Updated description");
        ResponseEntity<?> response = projectService.updateProjectById(id, data);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(projectRepository, times(0)).findById(id);
        verify(projectRepository, times(0)).save(any());
    }

    @Test
    public void testUpdateCertificateById_NullIdError() {
        Map<String, Object> data = new HashMap<>();
        data.put("description", "Updated description");
        ResponseEntity<?> response = projectService.updateProjectById(null, data);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(projectRepository, times(0)).findById(null);
        verify(projectRepository, times(0)).save(any());
    }

    @Test void testUpdateCertificateById_InvalidIdError() {
        String id = "Id_no_existe";
        Map<String, Object> data = new HashMap<>();
        data.put("description", "Updated description");
        ResponseEntity<?> response = projectService.updateProjectById(id, data);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(projectRepository, times(1)).findById(id);
        verify(projectRepository, times(0)).save(any());
    }

    @Test
    public void testDeleteById_Success() {
        Project project = new Project();
        when(projectRepository.findById(any())).thenReturn(Optional.of(project));

        ResponseEntity<String> response = projectService.deleteProjectById("123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(projectRepository, times(1)).findById("123");
        verify(projectRepository, times(1)).deleteById("123");
    }

    @Test
    public void testDeleteCertificateById_BlankIdError() {
        ResponseEntity<String> response = projectService.deleteProjectById("");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(projectRepository, times(0)).findById("");
        verify(projectRepository, times(0)).deleteById("");
    }

    @Test
    public void testDeleteCertificateById_NullIdError() {
        ResponseEntity<String> response = projectService.deleteProjectById(null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(projectRepository, times(0)).findById(null);
        verify(projectRepository, times(0)).deleteById(null);
    }

    @Test void testDeleteCertificateById_InvalidIdError() {
        ResponseEntity<String> response = projectService.deleteProjectById("Invalid_ID");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(projectRepository, times(1)).findById("Invalid_ID");
        verify(projectRepository, times(0)).deleteById("Invalid_ID");
    }

    private List<Project> projectList() {
        List<Project> list = new ArrayList<>();

        List<String> skillList  = new ArrayList<>();
        skillList.add("Skill1");
        skillList.add("Skill2");

        List<String> imageList = new ArrayList<>();
        imageList.add("image1");
        imageList.add("image2");

        Project project = new Project(null, "description", skillList, imageList);

        list.add(project);
        list.add(project);
        list.add(project);

        return list;
    }
}

