package es.gtorres.backend.controllers;

import es.gtorres.backend.entities.Project;
import es.gtorres.backend.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controlador para gestionar proyectos.
 * Proporciona endpoints para crear, actualizar, buscar y eliminar proyectos.
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /**
     * Agrega un nuevo proyecto a la base de datos.
     *
     * @param project El proyecto que se va a añadir a la base de datos.
     *                Debe cumplir con las restricciones de validación definidas en la clase Project.
     *
     * @return ResponseEntity con un mensaje indicando el resultado de la operación.
     *         Si la operación es exitosa, devuelve un ResponseEntity con un código de estado 200 (OK) y un mensaje
     *         indicando que el proyecto ha sido agregado correctamente. En caso de errores de validación,
     *         devuelve un ResponseEntity con un código de estado 400 (Bad Request) y un mensaje indicando los errores
     *         de validación encontrados.
     */
    @PostMapping("/addProject")
    public ResponseEntity<String> addProject(@RequestBody @Valid Project project) {
        return projectService.addProject(project);
    }

    /**
     * Recupera todos los proyectos de la base de datos.
     *
     * @return ResponseEntity con la lista de todos los proyectos recuperados de la base de datos.
     *         En caso de éxito, el ResponseEntity contendrá un código de estado 200 (OK) y la lista de proyectos.
     *         Si no se encuentra ningún proyecto, se devuelve un código de estado 204 (No Content).
     *         En caso de error al acceder a la base de datos, se devuelve un código de estado 500
     *         (Internal Server Error) con un mensaje de error.
     */
    @GetMapping("/findAllProject")
    public ResponseEntity<?> findAllProject() {
        return  projectService.findAllProject();
    }

    /**
     * Busca un proyecto por su ID en la base de datos y devuelve una ResponseEntity con el resultado.
     *
     * @param id El ID del proyecto que se desea buscar.
     *
     * @return ResponseEntity con el proyecto si se encuentra.
     *         En caso de éxito, el ResponseEntity contendrá un código de estado 200 (OK) y el proyecto encontrado.
     *         Si se proporciona un identificador no válido, se devuelve un código de estado 400 (Bad Request) indicando
     *         con un mensaje que dicho identificador no es válido.
     *         En caso de no encontrar el proyecto, se devuelve un código de estado 404 (Not Found) con un mensaje.
     *         Si ocurre un error al acceder a la base de datos, se devuelve un código de estado 500
     *         (Internal Server Error) con un mensaje de error.
     */
    @GetMapping("/findProjectById")
    public ResponseEntity<?> findProjectById(@RequestParam String id) {
        return  projectService.findProjectById(id);
    }

    /**
     * Actualiza un proyecto en la base de datos mediante su identificador proporcionado y los datos suministrados.
     *
     * @param id   El identificador del proyecto que se desea actualizar.
     * @param data Un mapa que contiene los campos a actualizar y sus nuevos valores.
     *             La clave representa el nombre del campo a actualizar y el valor representa el nuevo valor del campo.
     *
     * @return ResponseEntity que indica el resultado de la operación de actualización.
     *         En caso de éxito, devuelve un ResponseEntity con código de estado 200 (OK) y un mensaje indicando que el
     *         proyecto se ha actualizado correctamente.
     *         Si se proporciona un identificador inválido o nulo, devuelve un ResponseEntity con código de estado 400
     *         (Bad Request) y un mensaje de error.
     *         Si la solicitud de actualización no contiene datos válidos, devuelve un ResponseEntity con código de
     *         estado 400 (Bad Request) y un mensaje de error.
     *         Si el proyecto con el identificador proporcionado no se encuentra en la base de datos, devuelve un
     *         ResponseEntity con código de estado 404 (Not Found) y un mensaje de error.
     *         Si ocurre un error al acceder o actualizar la base de datos, devuelve un ResponseEntity con código de
     *         estado 500 (Internal Server Error) y un mensaje de error.
     */
    @PutMapping("/updateProjectById")
    public ResponseEntity<?> updateProjectById(@RequestParam String id, @RequestBody Map<String, Object> data) {
        return projectService.updateProjectById(id, data);
    }

    /**
     * Elimina un proyecto de la base de datos mediante el identificador proporcionado.
     *
     * @param id El identificador del proyecto que se desea eliminar.
     *
     * @return ResponseEntity que indica el resultado de la operación de eliminación.
     *         En caso de éxito, devuelve un ResponseEntity con código de estado 200 (OK) y un mensaje indicando que el
     *         proyecto se ha eliminado correctamente.
     *         Si se proporciona un identificador inválido o nulo, devuelve un ResponseEntity con código de estado 400
     *         (Bad Request) y un mensaje de error.
     *         Si no se encuentra ningún proyecto con el identificador proporcionado, devuelve un ResponseEntity con
     *         código de estado 404 (Not Found) y un mensaje de error.
     *         Si ocurre un error al acceder o eliminar el proyecto de la base de datos, devuelve un ResponseEntity
     *         con código de estado 500 (Internal Server Error) y un mensaje de error.
     */
    @DeleteMapping("/deleteProjectById")
    public ResponseEntity<String> deleteProjectById(@RequestParam String id) {
        return projectService.deleteProjectById(id);
    }
}
