// package es.ediae.master.programacion.gestionusuario.controller;

// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.annotation.Validated;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import jakarta.validation.Valid;

// @RestController
// @RequestMapping("/api/v1")
// @Validated
// public class ValidationController {

//     @PostMapping("/producto")
//     public ResponseEntity<Object> crearProducto(@Valid @RequestBody ProductoDTO productoDTO) {
//         if(productoDTO.getId() == 0) {
//             return ResponseEntity.badRequest().body("El ID no puede ser 0");
//         }
//         return ResponseEntity.badRequest().body(productoDTO);

//     }

// }
