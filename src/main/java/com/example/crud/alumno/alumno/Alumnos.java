package com.example.crud.alumno.alumno;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

@Entity
public class Alumnos
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @NotBlank(message= "Debe ingresar su nombre")
    private String nombre;
    @Column
    @NotBlank(message= "Debe ingresar su apellido")
    private String apellido;
    @Column
    @NumberFormat(pattern = "#0.00")
    private double nota1;
    @Column
    @NumberFormat(pattern = "#0.00")
    private double nota2;
    @Column
    @NumberFormat(pattern = "#0.00")
    private double nota3;
    @Column
    private double promedio; // Promedio de las 3 notas
    @Column
    private LocalDateTime fechaDeRegistro;

    public Alumnos() {
        super();
    }

    public Alumnos(long id, String nombre, String apellido, double nota1, double nota2, double nota3, double promedio, LocalDateTime fechaDeRegistro) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.promedio = promedio;
        this.fechaDeRegistro = fechaDeRegistro;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
        this.promedio = calcularPromedio();
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
        this.promedio = calcularPromedio();
    }

    public double getNota3() {
        return nota3;
    }

    public void setNota3(double nota3) {
        this.nota3 = nota3;
        this.promedio = calcularPromedio();
    }


    // Otros getters y setters omitidos por brevedad
    @NumberFormat(pattern = "#0.00")
    public double calcularPromedio() {
        return (nota1 + nota2 + nota3) / 3;
    }

    public LocalDateTime getFechaDeRegistro() {
        return fechaDeRegistro;
    }

    public void setFechaDeRegistro(LocalDateTime fechaDeRegistro) {
        this.fechaDeRegistro = fechaDeRegistro;
    }

    @PrePersist
    public void asignarRegistro()
    {
        fechaDeRegistro = LocalDateTime.now();
    }
}
