package es.gtorres.backend.controllers;

import es.gtorres.backend.entities.Project;
import es.gtorres.backend.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controlador para gestionar proyectos.
 * Proporciona endpoints para crear, actualizar, buscar y eliminar proyectos.
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectRepository projectRepository;

    /**
     * Constructor de ProjectController.
     * @param projectRepository Repositorio de proyectos.
     */
    @Autowired
    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    /**
     * Endpoint para agregar un nuevo proyecto.
     * @param project Objeto Project con la información del proyecto a agregar.
     * @return ResponseEntity con un mensaje indicando el resultado de la operación.
     */
    @PostMapping("/addProject")
    public ResponseEntity<String> addProject(@RequestBody Project project) {
        projectRepository.save(project);

        return ResponseEntity.ok("Proyecto almacenado correctamente en la base de datos");
    }

    /**
     * Endpoint para obtener todos los proyectos.
     * @return Lista de Project con todos los proyectos almacenados.
     */
    @GetMapping("/findAll")
    public List<Project> findAll() {
        return  projectRepository.findAll();
    }

    /**
     * Endpoint para buscar un proyecto por su ID.
     * @param id ID del proyecto a buscar.
     * @return ResponseEntity con el proyecto encontrado o un mensaje de error si no se encuentra.
     */
    @GetMapping("/findById")
    public ResponseEntity<Project> findById(@RequestParam String id) {
        Optional<Project> project = projectRepository.findById(id);

        if(project.isPresent()) {
            return ResponseEntity.ok(project.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint para actualizar un proyecto por su ID.
     * @param id ID del proyecto a actualizar.
     * @param newProject Objeto Project con la información actualizada.
     * @return ResponseEntity con un mensaje indicando el resultado de la actualización o un mensaje de error si no se encuentra el proyecto.
     */
    @PutMapping("/updateById")
    public ResponseEntity<String> updateById(@RequestParam String id, @RequestBody Project newProject) {
        Optional<Project> currentProjectOptional = projectRepository.findById(id);

        if(currentProjectOptional.isPresent()) {
            Project currentProject = currentProjectOptional.get();

            currentProject.setTitle(newProject.getTitle());
            currentProject.setDescription(newProject.getDescription());
            currentProject.setSkill(newProject.getSkill());
            currentProject.setImagesList(newProject.getImagesList());

            projectRepository.save(currentProject);

            return ResponseEntity.ok("Proyecto actualizado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el proyecto con ID: " + id);
        }
    }

    /**
     * Endpoint para actualizar el título de un proyecto por su ID.
     * @param id ID del proyecto a actualizar.
     * @param updatedTitle Objeto Map que contiene el título para actualizar.
     * @return ResponseEntity con un mensaje indicando el resultado de la actualización o un mensaje de error si encuentra algún problema.
     */
    @PatchMapping("/updateTitleById")
    public ResponseEntity<String> updateTitleById(@RequestParam String id, @RequestBody Map<String, String> updatedTitle) {
        Optional<Project> currentProjectOptional = projectRepository.findById(id);

        if (currentProjectOptional.isPresent()) {
            Project currentProject = currentProjectOptional.get();

            if (updatedTitle.containsKey("title")) {
                String newTitle = updatedTitle.get("title");

                if(newTitle != null && !newTitle.isEmpty()) {
                    currentProject.setTitle(newTitle);
                    projectRepository.save(currentProject);

                    return ResponseEntity.ok("Título del proyecto actualizado correctamente");
                } else {
                    return ResponseEntity.badRequest().body("El titulo proporcionado no es valido");
                }
            } else {
                return ResponseEntity.badRequest().body("La solicitud no contiene datos válidos para actualizar el título");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el proyecto con ID: " + id);
        }
    }

    /**
     * Endpoint para actualizar la descripción de un proyecto por su ID.
     * @param id ID del proyecto a actualizar.
     * @param updatedDescription Objeto Map que contiene la descripción para actualizar.
     * @return ResponseEntity con un mensaje indicando el resultado de la actualización o un mensaje de error si encuentra algún problema.
     */
    @PatchMapping("/updateDescriptionById")
    public ResponseEntity<String> updateDescriptionById(@RequestParam String id, @RequestBody Map<String, String> updatedDescription) {
        Optional<Project> currentProjectOptional = projectRepository.findById(id);

        if (currentProjectOptional.isPresent()) {
            Project currentProject = currentProjectOptional.get();

            if (updatedDescription.containsKey("description")) {
                String newDescription = updatedDescription.get("description");

                if(newDescription != null && !newDescription.isEmpty()) {
                    currentProject.setDescription(newDescription);
                    projectRepository.save(currentProject);

                    return ResponseEntity.ok("Descripción del proyecto actualizada correctamente");
                } else {
                    return ResponseEntity.badRequest().body("La descripción proporcionada no es válida");
                }
            } else {
                return ResponseEntity.badRequest().body("La solicitud no contiene datos válidos para actualizar la descripción");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el proyecto con ID: " + id);
        }
    }

    /**
     * Endpoint para actualizar la lista de skills de un proyecto por su ID.
     * @param id ID del proyecto a actualizar.
     * @param newSkillsLists Objeto List que contiene la lista de skill para actualizar.
     * @return ResponseEntity con un mensaje indicando el resultado de la actualización o un mensaje de error si encuentra algún problema.
     */
    @PatchMapping("/updateSkillsById")
    public ResponseEntity<String> updateSkillsById(@RequestParam String id, @RequestBody List<String> newSkillsLists) {
        Optional<Project> currentProjectOptional = projectRepository.findById(id);

        if (currentProjectOptional.isPresent()) {
            Project currentProject = currentProjectOptional.get();

            if(newSkillsLists != null && !newSkillsLists.isEmpty()) {
                currentProject.setSkill(newSkillsLists);
                projectRepository.save(currentProject);

                return ResponseEntity.ok("Habilidades del proyecto actualizadas correctamente");
            } else {
                return  ResponseEntity.badRequest().body("La lista de skills proporcionada no es valida");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el proyecto con ID: " + id);
        }
    }

    /**
     * Endpoint para actualizar la lista de imágenes de un proyecto por su ID.
     * @param id ID del proyecto a actualizar.
     * @param newImagesList Objeto List que contiene la lista de imágenes para actualizar.
     * @return ResponseEntity con un mensaje indicando el resultado de la actualización o un mensaje de error si encuentra algún problema.
     */
    @PatchMapping("/updateImagesById")
    public ResponseEntity<String> updateImagesById(@RequestParam String id, @RequestBody List<String> newImagesList) {
        Optional<Project> currentProjectOptional = projectRepository.findById(id);

        if (currentProjectOptional.isPresent()) {
            Project currentProject = currentProjectOptional.get();

            if(newImagesList != null && !newImagesList.isEmpty()) {
                currentProject.setImagesList(newImagesList);
                projectRepository.save(currentProject);

                return ResponseEntity.ok("Habilidades del proyecto actualizadas correctamente");
            } else {
                return ResponseEntity.badRequest().body("La lista de imagenes proporcionada no es valida");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el proyecto con ID: " + id);
        }
    }

    /**
     * Endpoint para eliminar un proyecto por su ID.
     * @param id ID del proyecto a eliminar.
     * @return ResponseEntity con un mensaje indicando el resultado de eliminar el proyecto o un mensaje de error si encuentra algún problema.
     */
    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteById(@RequestParam String id) {
        try {
            projectRepository.deleteById(id);
            return ResponseEntity.ok("Proyecto eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se produjo un error mientras se eliminaba el proyecto");
        }
    }
}
