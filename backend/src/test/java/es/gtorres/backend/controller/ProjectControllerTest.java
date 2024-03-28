package es.gtorres.backend.controller;

import es.gtorres.backend.controllers.ProjectController;
import es.gtorres.backend.entities.Project;
import es.gtorres.backend.services.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectControllerTest {

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddProject_Success() {
        Project validProject = createValidProject();
        when(projectService.addProject(validProject)).thenReturn(ResponseEntity.ok("Proyecto agregado correctamente"));

        ResponseEntity<String> response = projectController.addProject(validProject);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Proyecto agregado correctamente", response.getBody());
    }

    @Test
    public void testAddProject_ValidationFailure() {
        Project invalidProject = createInvalidProject();
        when(projectService.addProject(invalidProject))
                .thenReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Validación de datos incorrecta"));

        ResponseEntity<String> response = projectController.addProject(invalidProject);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Validación de datos incorrecta", response.getBody());
    }

    @Test
    public void testAddProject_NullProject() {
        when(projectService.addProject(null))
                .thenReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Proyecto nulo"));

        ResponseEntity<String> response = projectController.addProject(null);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Proyecto nulo", response.getBody());
    }

    @Test
    public void testFindAllProject_WithData() {
        List<Project> project = createProject();
        ResponseEntity<List<Project>> responseEntity = ResponseEntity.ok(project);
        doReturn(responseEntity).when(projectService).findAllProject();

        ResponseEntity<?> response = projectController.findAllProject();

        assertEquals(HttpStatus.OK, projectController.findAllProject().getStatusCode());
        assertEquals(project, response.getBody());
    }

    @Test
    public void testFindAllProject_EmptyData() {
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Ningún proyecto encontrado");
        doReturn(responseEntity).when(projectService).findAllProject();

        ResponseEntity<?> response = projectController.findAllProject();

        assertEquals(HttpStatus.NO_CONTENT, projectController.findAllProject().getStatusCode());
        assertEquals("Ningún proyecto encontrado", response.getBody());
    }

    @Test
    public void testFindAllProject_DataBaseError() {
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al acceder a la base de datos");
        doReturn(responseEntity).when(projectService).findAllProject();

        ResponseEntity<?> response = projectController.findAllProject();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error al acceder a la base de datos", response.getBody());
    }

    @Test
    public void testFindProjectById_Success() {
        String validId = "validId";
        Project project = createValidProject();
        ResponseEntity<Project> responseEntity = ResponseEntity.ok(project);
        doReturn(responseEntity).when(projectService).findProjectById(validId);

        ResponseEntity<?> response = projectController.findProjectById(validId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(project, response.getBody());
    }

    @Test
    public void testFindProjectById_InvalidId() {
        String invalidId = "invalidId";
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Identificador no valido");
        doReturn(responseEntity).when(projectService).findProjectById(invalidId);

        ResponseEntity<?> response = projectController.findProjectById(invalidId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Identificador no valido", response.getBody());
    }

    @Test
    public void testFindProjectById_CertificateNoFound() {
        String incorrectId = "incorrectId";
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró proyecto con ese identificador");
        doReturn(responseEntity).when(projectService).findProjectById(incorrectId);

        ResponseEntity<?> response = projectController.findProjectById(incorrectId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No se encontró proyecto con ese identificador", response.getBody());
    }

    @Test
    public void testFindProjectById_DataBaseError() {
        String correctId = "correctId";
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al acceder a la base de datos");
        doReturn(responseEntity).when(projectService).findProjectById(correctId);

        ResponseEntity<?> response = projectController.findProjectById(correctId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error al acceder a la base de datos", response.getBody());
    }

    @Test
    public void testUpdateProjectById_Success() {
        String validId = "validId";
        Map<String, Object> validData = new HashMap<>();
        ResponseEntity<String> responseEntity = ResponseEntity.ok("Proyecto actualizado con éxito");
        doReturn(responseEntity).when(projectService).updateProjectById(validId, validData);

        ResponseEntity<?> response = projectController.updateProjectById(validId, validData);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Proyecto actualizado con éxito", response.getBody());
    }

    @Test
    public void testUpdateProjectById_InvalidId() {
        String invalidId = "invalidId";
        Map<String, Object> validData = new HashMap<>();
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Identificador no valido");
        doReturn(responseEntity).when(projectService).updateProjectById(invalidId, validData);

        ResponseEntity<?> response = projectController.updateProjectById(invalidId, validData);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Identificador no valido", response.getBody());
    }

    @Test
    public void testUpdateProjectById_InvalidData() {
        String validId = "validId";
        Map<String, Object> invalidData = new HashMap<>();
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos no validos");
        doReturn(responseEntity).when(projectService).updateProjectById(validId, invalidData);

        ResponseEntity<?> response = projectController.updateProjectById(validId, invalidData);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Datos no validos", response.getBody());
    }

    @Test
    public void testUpdateProjectById_ProjectNoFound() {
        String incorrectId = "incorrectId";
        Map<String, Object> validData = new HashMap<>();
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró proyecto con ese identificador");
        doReturn(responseEntity).when(projectService).updateProjectById(incorrectId, validData);

        ResponseEntity<?> response = projectController.updateProjectById(incorrectId, validData);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No se encontró proyecto con ese identificador", response.getBody());
    }

    @Test
    public void testUpdateProjectById_DataBaseError() {
        String correctId = "correctId";
        Map<String, Object> validData = new HashMap<>();
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al acceder a la base de datos");
        doReturn(responseEntity).when(projectService).updateProjectById(correctId, validData);

        ResponseEntity<?> response = projectController.updateProjectById(correctId, validData);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error al acceder a la base de datos", response.getBody());
    }

    @Test
    public void testDeleteProjectById_Success() {
        String validId = "validId";
        ResponseEntity<String> responseEntity = ResponseEntity.ok("Proyecto eliminado correctamente");
        doReturn(responseEntity).when(projectService).deleteProjectById(validId);

        ResponseEntity<?> response = projectController.deleteProjectById(validId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Proyecto eliminado correctamente", response.getBody());
    }

    @Test
    public void testDeleteProjectById_InvalidId() {
        String invalidId = "invalidId";
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Identificador no valido");
        doReturn(responseEntity).when(projectService).deleteProjectById(invalidId);

        ResponseEntity<?> response = projectController.deleteProjectById(invalidId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Identificador no valido", response.getBody());
    }

    @Test
    public void testDeleteProjectById_ProjectNoFound() {
        String incorrectId = "incorrectId";
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró proyecto con ese identificador");
        doReturn(responseEntity).when(projectService).deleteProjectById(incorrectId);

        ResponseEntity<?> response = projectController.deleteProjectById(incorrectId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No se encontró proyecto con ese identificador", response.getBody());
    }

    @Test
    public void testDeleteProyectoById_DataBaseError() {
        String correctId = "correctId";
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al acceder a la base de datos");
        doReturn(responseEntity).when(projectService).deleteProjectById(correctId);

        ResponseEntity<?> response = projectController.deleteProjectById(correctId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error al acceder a la base de datos", response.getBody());
    }

    private Project createValidProject() {
        List<String> skillList  = new ArrayList<>();
        skillList.add("Skill1");
        skillList.add("Skill2");

        List<String> imageList = new ArrayList<>();
        imageList.add("image1");
        imageList.add("image2");

        return new Project(
                "Esto es un titulo",
                "Esta es la descripción",
                skillList,
                imageList);
    }

    private Project createInvalidProject() {
        List<String> skillList  = new ArrayList<>();
        skillList.add("Skill1");
        skillList.add("Skill2");

        List<String> imageList = new ArrayList<>();
        imageList.add("image1");
        imageList.add("image2");

        return new Project(
                "Esto es un titulo",
                "",
                skillList,
                imageList);
    }

    private List<Project> createProject() {
        List<Project> projects = new ArrayList<>();
        projects.add(createValidProject());
        projects.add(createValidProject());
        projects.add(createValidProject());

        return projects;
    }
}

