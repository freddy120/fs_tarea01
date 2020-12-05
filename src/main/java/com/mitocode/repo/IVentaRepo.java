package com.mitocode.repo;

import com.mitocode.model.Venta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

//@Repository
public interface IVentaRepo extends IGenericRepo<Venta, Integer>{
  //@Query(JPQL)
  @Query("FROM Venta c WHERE LOWER(c.persona.nombres) LIKE %:nombreCompleto% OR LOWER(c.persona.apellidos) LIKE %:nombreCompleto%")
  List<Venta> buscar(@Param("nombreCompleto") String nombreCompleto);

  // X>= BETEWEEN Y< | 14-11-2020 - 15-11-2020 | ISODate yyyy-mm-ddTHH:mm:ss.mm
  @Query("FROM Venta c WHERE c.fecha BETWEEN :fechaConsulta AND :fechaSgte")
  List<Venta> buscarFecha(@Param("fechaConsulta") LocalDateTime fechaConsulta, @Param("fechaSgte") LocalDateTime fechaSgte);


}