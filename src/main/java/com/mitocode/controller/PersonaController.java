package com.mitocode.controller;

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Persona;
import com.mitocode.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/personas")
public class PersonaController {

  @Autowired
  private IPersonaService service;

  @GetMapping
  public ResponseEntity<List<Persona>> listar() throws Exception {
    List<Persona> lista = service.listar();
    return new ResponseEntity<>(lista, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Persona> listarPorId(@PathVariable("id") Integer id) throws Exception {
    Persona obj = service.listarPorId(id);

    if (obj == null) {
      throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
    }
    return new ResponseEntity<>(obj, HttpStatus.OK);
  }

  @GetMapping("/hateoas/{id}")
  public EntityModel<Persona> listarPorIdHateoas(@PathVariable("id") Integer id) throws Exception {
    Persona obj = service.listarPorId(id);

    if (obj.getIdPersona() == null) {
      throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
    }

    // localhost:8080/pacientes/{id}
    EntityModel<Persona> recurso = EntityModel.of(obj);

    WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));

    recurso.add(linkTo.withRel("persona-recurso"));

    return recurso;
  }

  /*
   * @PostMapping public ResponseEntity<Paciente> registrar(@Valid @RequestBody
   * Paciente p) { Paciente obj = service.registrar(p); return new
   * ResponseEntity<Paciente>(obj, HttpStatus.CREATED); }
   */

  @PostMapping
  public ResponseEntity<Persona> registrar(@Valid @RequestBody Persona p) throws Exception {
    Persona obj = service.registrar(p);

    // localhost:8080/pacientes/2
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(obj.getIdPersona()).toUri();
    return ResponseEntity.created(location).build();
  }

  @PutMapping
  public ResponseEntity<Persona> modificar(@Valid @RequestBody Persona p) throws Exception {
    Persona obj = service.modificar(p);
    return new ResponseEntity<>(obj, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
    Persona obj = service.listarPorId(id);

    if (obj == null) {
      throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
    }

    service.eliminar(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }


  @GetMapping("/pageable")
  public ResponseEntity<Page<Persona>> listarPageable(Pageable pageable) throws Exception{
    Page<Persona> pacientes = service.listarPageable(pageable);
    return new ResponseEntity<>(pacientes, HttpStatus.OK);
  }
}
