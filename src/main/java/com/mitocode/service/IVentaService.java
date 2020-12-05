package com.mitocode.service;

import com.mitocode.dto.FiltroConsultaDTO;
import com.mitocode.model.Venta;

import java.time.LocalDateTime;
import java.util.List;

public interface IVentaService extends ICRUD<Venta, Integer>{

	List<Venta> buscar(FiltroConsultaDTO filtro);

	List<Venta> buscarFecha(LocalDateTime fecha);

}
