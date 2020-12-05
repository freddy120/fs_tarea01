package com.mitocode.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "detalle_venta")
@Data
public class DetalleVenta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idDetalleVenta;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "id_venta", nullable = false, foreignKey = @ForeignKey(name = "FK_venta_detalle"))
  private Venta venta;

  //@JsonIgnore
  @ManyToOne
  @JoinColumn(name = "id_producto", nullable = false, foreignKey = @ForeignKey(name = "FK_producto_detalle"))
  private Producto producto;

  @Column(name = "cantidad", nullable = false)
  private Integer cantidad;

}
