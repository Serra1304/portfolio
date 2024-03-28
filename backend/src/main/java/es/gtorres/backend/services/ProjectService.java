package es.gtorres.backend.services;

import es.gtorres.backend.entities.Project;
import es.gtorres.backend.repositories.ProjectRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Servicio para realizar operaciones CRUD relacionadas con proyectos.
 */
@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    /**
     * Agrega un nuevo proyecto a la base de datos.
     *
     * @param project El proyecto que se va a guardar en la base de datos.
     *                Debe cumplir con las restricciones de validación definidas en la clase Project.
     *
     * @return ResponseEntity con un mensaje indicando el resultado de la operación. Si la operación es exitosa,
     *         devuelve un ResponseEntity con un código de estado 200 (OK) y un mensaje indicando que el proyecto
     *         ha sido agregado correctamente. En caso de errores al acceder a la base de datos, devuelve un
     *         ResponseEntity con un código de estado 500 (Internal Server Error) y un mensaje indicando que ha
     *         ocurrido un error al acceder a la base de datos.
     */
    public ResponseEntity<String> addProject(@NonNull @Valid Project project) {
        try {
            projectRepository.save(project);
            return ResponseEntity.ok("Proyecto guardado correctamente.");
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ha ocurrido un error mientras se guardaba el proyecto en la base de datos.");
        }
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
    public ResponseEntity<?> findAllProject() {
        try {
            List<Project> projects = projectRepository.findAll();
            if (projects.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body("No se encontró ningún proyecto en la base de datos");
            } else {
                return ResponseEntity.ok(projects);
            }
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al recuperar los proyectos de la base de datos.");
        }
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
    public ResponseEntity<?> findProjectById(String id) {
        try {
            if (id == null || id.isEmpty()) {
                return ResponseEntity.badRequest().body("El identificador especificado no es válido");
            }

            Optional<Project> projectOptional = projectRepository.findById(id);
            if (projectOptional.isPresent()) {
                return ResponseEntity.ok(projectOptional.get());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró ningún proyecto con el identificador proporcionado");
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al recuperar el proyecto de la base de datos.");
        }
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
    public ResponseEntity<?> updateProjectById(String id, Map<String, Object> data) {
        try {
            // Validación de 'id' y 'data'.
            if (id == null || id.isEmpty()) {
                return ResponseEntity.badRequest().body("El identificador especificado no es válido.");
            }
            if (data == null || data.isEmpty()) {
                return ResponseEntity.badRequest().body("La solicitud no contiene datos validos.");
            }

            // Se comprueba la existencia del proyecto con el 'id' proporcionado.
            Optional<Project> projectOptional = projectRepository.findById(id);
            if (projectOptional.isPresent()) {
                Project project = projectOptional.get();

                // Se mapea data.
                for (Map.Entry<String, Object> entry : data.entrySet()) {
                    String field = entry.getKey();
                    Object value = entry.getValue();

                    if (field.equals("id")) {
                        return ResponseEntity.badRequest().body("No esta permitido actualizar el campo 'id'.");
                    }

                    // Se actualiza el parámetro correspondiente a 'field' del proyecto a través de reflexión.
                    try {
                        String fieldName = Character.toUpperCase(field.charAt(0)) + field.substring(1);

                        if(value instanceof String valueString) {
                            Method setter = Project.class.getMethod("set" + fieldName, String.class);
                            setter.invoke(project, valueString);
                        }

                        if(value instanceof List) {
                            Method setter = Project.class.getMethod("set" + fieldName, List.class);
                            @SuppressWarnings("unchecked")
                            List<String> valueList = (List<String>) value;
                            setter.invoke(project, valueList);
                        }
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                        return ResponseEntity.badRequest()
                                .body("El campo '" + field + "' no existe o no esta permitida su modificación en el proyecto.");
                    }
                }
                // Se guarda el certificado en la base de datos.
                projectRepository.save(project);
                return ResponseEntity.ok("Proyecto actualizado correctamente.");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró ningún proyecto con el identificador proporcionado.");
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el proyecto de la base de datos.");
        }
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
    public ResponseEntity<String> deleteProjectById(String id) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().body("El identificador proporcionado no es valido.");
        }

        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            try {
                projectRepository.deleteById(id);
                return ResponseEntity.ok("Proyecto eliminado correctamente.");
            } catch (DataAccessException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Se produjo un error mientras se eliminaba el proyecto.");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No se encontró ningún proyecto con el identificador proporcionado.");
    }
}
