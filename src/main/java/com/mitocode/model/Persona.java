package com.mitocode.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Schema(description = "Persona Model")
@Entity
//@Table(name = "Persona") //other table name
@Data
public class Persona {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idPersona;

  @Schema(description = "nombres de persona")
  @Size(min = 3, message = "{nombres.size}", max = 70)
  @Column(name = "nombres", nullable = false, length = 70)
  private String nombres;

  @NotNull
  @Size(min = 3, message = "{apellidos.size}", max = 70)
  @Schema(description = "apellidos de persona")
  //@JsonProperty("apellidos_other_name_in_json")
  @Column(name = "apellidos", nullable = false, length = 70)
  private String apellidos;
}
