package com.mitocode.service.Impl;

import com.mitocode.dto.FiltroConsultaDTO;
import com.mitocode.model.Venta;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IVentaRepo;
import com.mitocode.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentaServiceImpl extends CRUDImpl<Venta, Integer> implements IVentaService {

  @Autowired
  private IVentaRepo repo;


  @Override
  protected IGenericRepo<Venta, Integer> getRepo() {
    return repo;
  }

  @Override
  public List<Venta> buscar(FiltroConsultaDTO filtro) {
    return repo.buscar(filtro.getNombreCompleto());
  }

  @Override
  public List<Venta> buscarFecha(LocalDateTime fecha) {
    return repo.buscarFecha(fecha, fecha.plusDays(1));
  }

}
