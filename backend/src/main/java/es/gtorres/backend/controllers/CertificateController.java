package es.gtorres.backend.controllers;

import es.gtorres.backend.entities.Certificate;
import es.gtorres.backend.services.CertificateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/certificate")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    /**
     * Agrega un nuevo certificado a la base de datos.
     *
     * @param certificate El certificado que se va a añadir a la base de datos.
     *                    Debe cumplir con las restricciones de validación definidas en la clase Certificate.
     *
     * @return ResponseEntity con un mensaje indicando el resultado de la operación.
     *         Si la operación es exitosa, devuelve un ResponseEntity con un código de estado 200 (OK) y un mensaje
     *         indicando que el certificado ha sido agregado correctamente. En caso de errores de validación,
     *         devuelve un ResponseEntity con un código de estado 400 (Bad Request) y un mensaje indicando los errores
     *         de validación encontrados.
     */
    @PostMapping("/addCertificate")
    public ResponseEntity<String> addCertificate(@RequestBody @Valid Certificate certificate) {
        return certificateService.addCertificate(certificate);
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
    @GetMapping("/findAllCertificates")
    public ResponseEntity<?> findAllCertificates() {
        return  certificateService.findAllCertificates();
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
    @GetMapping("/findById")
    public ResponseEntity<?> findCertificateById(@RequestParam String id) {
        return certificateService.findCertificateById(id);
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
    @PatchMapping("updateById")
    public ResponseEntity<?> updateCertificateById(@RequestParam String id, @RequestBody Map<String, String> data) {
        return certificateService.updateCertificateById(id, data);
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
    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteCertificateById(@RequestParam String id) {
        return certificateService.deleteById(id);
    }
}
