package com.mitocode.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Schema(description = "Producto Model")
@Entity
@Data
public class Producto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idProducto;

  @Schema(description = "nombres del producto")
  @Size(min = 3, message = "{nombre.size}", max = 70)
  @Column(name = "nombre", nullable = false, length = 70)
  private String nombre ;

  @NotNull
  @Size(min = 3, message = "{marca.size}", max = 70)
  @Schema(description = "marca del producto")
  @Column(name = "marca", nullable = false, length = 70)
  private String marca;

}
