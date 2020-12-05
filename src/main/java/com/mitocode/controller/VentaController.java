package com.mitocode.controller;

import com.mitocode.dto.FiltroConsultaDTO;
import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Venta;
import com.mitocode.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/ventas")
public class VentaController {

	@Autowired
	private IVentaService service;
	

	@GetMapping
	public ResponseEntity<List<Venta>> listar() throws Exception {
		List<Venta> lista = service.listar();
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Venta> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Venta obj = service.listarPorId(id);

		if (obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@GetMapping("/hateoas/{id}")
	public EntityModel<Venta> listarPorIdHateoas(@PathVariable("id") Integer id) throws Exception {
		Venta obj = service.listarPorId(id);

		if (obj.getIdVenta() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}

		// localhost:8080/Consultas/{id}
		EntityModel<Venta> recurso = EntityModel.of(obj);

		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));

		recurso.add(linkTo.withRel("Venta-recurso"));

		return recurso;
	}

	@PostMapping
	public ResponseEntity<Venta> registrar(@Valid @RequestBody Venta v) throws Exception {

		v.getDetalleVenta().forEach(det -> det.setVenta(v));

		Venta obj = service.registrar(v);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getIdVenta()).toUri();
		return ResponseEntity.created(location).build();
	}

	
	@GetMapping("/buscar")
	public ResponseEntity<List<Venta>> buscarFecha(@RequestParam("fecha") String fecha) {
		List<Venta> consultas;
		
		consultas = service.buscarFecha(LocalDateTime.parse(fecha));						
		
		return new ResponseEntity<>(consultas, HttpStatus.OK);
	}
	
	@PostMapping("/buscar/otros")
	public ResponseEntity<List<Venta>> buscarOtro(@RequestBody FiltroConsultaDTO filtro) {
		List<Venta> consultas;
		
		consultas = service.buscar(filtro);			
		
		return new ResponseEntity<>(consultas, HttpStatus.OK);
	}

	

	
	
}
