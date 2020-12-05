package com.mitocode.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "venta")
@Getter
@Setter
public class Venta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idVenta;

  @Column(name = "fecha", nullable = false)
  private LocalDateTime fecha;

  @ManyToOne
  @JoinColumn(name = "id_persona", nullable = false, foreignKey = @ForeignKey(name = "FK_venta_persona"))
  private Persona persona;

  @Column(name = "importe", nullable = false)
  private Double importe;

  @OneToMany(mappedBy = "venta", cascade = { CascadeType.ALL }, orphanRemoval = true)
  private List<DetalleVenta> detalleVenta;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((idVenta == null) ? 0 : idVenta.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Venta other = (Venta) obj;
    if (idVenta == null) {
      if (other.idVenta != null)
        return false;
    } else if (!idVenta.equals(other.idVenta))
      return false;
    return true;
  }


}
