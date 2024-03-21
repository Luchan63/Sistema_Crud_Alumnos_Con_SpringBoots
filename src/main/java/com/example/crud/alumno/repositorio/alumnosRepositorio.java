package com.example.crud.alumno.repositorio;


import com.example.crud.alumno.alumno.Alumnos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface alumnosRepositorio extends JpaRepository<Alumnos, Long>
{
}
