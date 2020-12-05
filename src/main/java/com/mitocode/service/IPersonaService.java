package com.mitocode.service;

import com.mitocode.model.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPersonaService extends ICRUD<Persona, Integer>{
		
	Page<Persona> listarPageable(Pageable pageable);

}
