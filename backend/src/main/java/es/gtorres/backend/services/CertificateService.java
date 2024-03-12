package es.gtorres.backend.services;

import es.gtorres.backend.entities.Certificate;
import es.gtorres.backend.repositories.CertificateRepository;
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

@Service
public class CertificateService {

    @Autowired
    CertificateRepository certificateRepository;

    /**
     * Agrega un nuevo certificado a la base de datos.
     *
     * @param certificate El certificado que se va a guardar en la base de datos.
     *                    Debe cumplir con las restricciones de validación definidas en la clase Certificate.
     *
     * @return ResponseEntity con un mensaje indicando el resultado de la operación. Si la operación es exitosa,
     *         devuelve un ResponseEntity con un código de estado 200 (OK) y un mensaje indicando que el certificado
     *         ha sido agregado correctamente. En caso de errores al acceder a la base de datos, devuelve un
     *         ResponseEntity con un código de estado 500 (Internal Server Error) y un mensaje indicando que ha
     *         ocurrido un error al acceder a la base de datos.
     */
    public ResponseEntity<String> addCertificate(@NonNull @Valid Certificate certificate) {
        try {
            certificateRepository.save(certificate);
            return ResponseEntity.ok("Certificado guardado correctamente.");
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ha ocurrido un error mientras se guardaba el certificado en la base de datos.");
        }
    }

    /**
     * Recupera todos los certificados de la base de datos.
     *
     * @return ResponseEntity con la lista de todos los certificados recuperados de la base de datos.
     *         En caso de éxito, el ResponseEntity contendrá un código de estado 200 (OK) y la lista de certificados.
     *         Si no se encuentra ningún certificado, se devuelve un código de estado 204 (No Content).
     *         En caso de error al acceder a la base de datos, se devuelve un código de estado 500
     *         (Internal Server Error) con un mensaje de error.
     */
    public ResponseEntity<?> findAllCertificates() {
        try {
            List<Certificate> certificates = certificateRepository.findAll();
            if (certificates.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body("No se encontró ningún certificado en la base de datos");
            } else {
                return ResponseEntity.ok(certificates);
            }
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al recuperar los certificados de la base de datos.");
        }
    }

    /**
     * Busca un certificado por su ID en la base de datos y devuelve una ResponseEntity con el resultado.
     *
     * @param id El ID del certificado que se desea buscar.
     *
     * @return ResponseEntity con el certificado si se encuentra.
     *         En caso de éxito, el ResponseEntity contendrá un código de estado 200 (OK) y el certificado encontrado.
     *         Si se proporciona un identificador no válido, se devuelve un código de estado 400 (Bad Request) indicando
     *         con un mensaje que dicho identificador no es válido.
     *         En caso de no encontrar el certificado, se devuelve un código de estado 404 (Not Found) con un mensaje.
     *         Si ocurre un error al acceder a la base de datos, se devuelve un código de estado 500
     *         (Internal Server Error) con un mensaje de error.
     */
    public ResponseEntity<?> findCertificateById(String id) {
        try {
            if (id == null || id.isEmpty()) {
                return ResponseEntity.badRequest().body("El identificador especificado no es válido");
            }

            Optional<Certificate> certificateOptional = certificateRepository.findById(id);
            if (certificateOptional.isPresent()) {
                return ResponseEntity.ok(certificateOptional.get());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró ningún certificado con el identificador proporcionado");
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al recuperar el certificado de la base de datos.");
        }
    }

    /**
     * Actualiza un certificado en la base de datos mediante su identificador proporcionado y los datos suministrados.
     *
     * @param id   El identificador del certificado que se desea actualizar.
     * @param data Un mapa que contiene los campos a actualizar y sus nuevos valores.
     *             La clave representa el nombre del campo a actualizar y el valor representa el nuevo valor del campo.
     *
     * @return ResponseEntity que indica el resultado de la operación de actualización.
     *         En caso de éxito, devuelve un ResponseEntity con código de estado 200 (OK) y un mensaje indicando que el
     *         certificado se ha actualizado correctamente.
     *         Si se proporciona un identificador inválido o nulo, devuelve un ResponseEntity con código de estado 400
     *         (Bad Request) y un mensaje de error.
     *         Si la solicitud de actualización no contiene datos válidos, devuelve un ResponseEntity con código de
     *         estado 400 (Bad Request) y un mensaje de error.
     *         Si el certificado con el identificador proporcionado no se encuentra en la base de datos, devuelve un
     *         ResponseEntity con código de estado 404 (Not Found) y un mensaje de error.
     *         Si ocurre un error al acceder o actualizar la base de datos, devuelve un ResponseEntity con código de
     *         estado 500 (Internal Server Error) y un mensaje de error.
     */
    public ResponseEntity<?> updateCertificateById(String id, Map<String, String> data) {
        try {
            // Validación de 'id' y 'data'.
            if (id == null || id.isEmpty()) {
                return ResponseEntity.badRequest().body("El identificador especificado no es válido.");
            }
            if (data == null || data.isEmpty()) {
                return ResponseEntity.badRequest().body("La solicitud no contiene datos validos.");
            }

            // Se comprueba la existencia del certificado con el 'id' proporcionado.
            Optional<Certificate> certificateOptional = certificateRepository.findById(id);
            if (certificateOptional.isPresent()) {
                Certificate certificate = certificateOptional.get();

                // Se mapea data.
                for (Map.Entry<String, String> entry : data.entrySet()) {
                    String field = entry.getKey();
                    String value = entry.getValue();

                    if (field.equals("id")) {
                        return ResponseEntity.badRequest().body("No esta permitido actualizar el campo 'id'.");
                    }

                    // Se actualiza el parámetro correspondiente a 'field' del certificado a través de reflexión.
                    try {
                    String fieldName = Character.toUpperCase(field.charAt(0)) + field.substring(1);
                    Method setter = Certificate.class.getMethod("set" + fieldName, String.class);
                    setter.invoke(certificate, value);
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                        return ResponseEntity.badRequest()
                                .body("El campo '" + field + "' no existe o no esta permitida su modificación en el certificado.");
                    }
                }
                // Se guarda el certificado en la base de datos.
                certificateRepository.save(certificate);
                return ResponseEntity.ok("Certificado actualizado correctamente.");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró ningún certificado con el identificador proporcionado.");
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el certificado de la base de datos.");
        }
    }

    /**
     * Elimina un certificado de la base de datos mediante el identificador proporcionado.
     *
     * @param id El identificador del certificado que se desea eliminar.
     *
     * @return ResponseEntity que indica el resultado de la operación de eliminación.
     *         En caso de éxito, devuelve un ResponseEntity con código de estado 200 (OK) y un mensaje indicando que el
     *         certificado se ha eliminado correctamente.
     *         Si se proporciona un identificador inválido o nulo, devuelve un ResponseEntity con código de estado 400
     *         (Bad Request) y un mensaje de error.
     *         Si no se encuentra ningún certificado con el identificador proporcionado, devuelve un ResponseEntity con
     *         código de estado 404 (Not Found) y un mensaje de error.
     *         Si ocurre un error al acceder o eliminar el certificado de la base de datos, devuelve un ResponseEntity
     *         con código de estado 500 (Internal Server Error) y un mensaje de error.
     */
    public ResponseEntity<String> deleteById(String id) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().body("El identificador proporcionado no es valido.");
        }

        Optional<Certificate> certificateOptional = certificateRepository.findById(id);
        if (certificateOptional.isPresent()) {
            try {
                certificateRepository.deleteById(id);
                return ResponseEntity.ok("Certificado eliminado correctamente.");
            } catch (DataAccessException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Se produjo un error mientras se eliminaba el certificado.");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No se encontró ningún certificado con el identificador proporcionado.");
    }
}
