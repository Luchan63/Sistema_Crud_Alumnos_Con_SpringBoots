package com.example.crud.alumno.controlador;

import com.example.crud.alumno.alumno.Alumnos;
import com.example.crud.alumno.repositorio.alumnosRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
public class Controlador
{
    @Autowired
    alumnosRepositorio alumnoRepository;


    @GetMapping(value = {"/",""})
    public String verPaginaInicio(Model model)
    {
        List<Alumnos> alumnos = alumnoRepository.findAll();
        model.addAttribute("alumnos",alumnos);
        return "index";
    }

    @GetMapping(value = "/nuevo")
    public String formularioRegistro(Model model)
    {
        model.addAttribute("alumno", new Alumnos());

        return "nuevo";
    }

    @PostMapping(value = "/nuevo")
    public String guardarContacto(@Valid Alumnos alumno, BindingResult bindingResult, RedirectAttributes redirect, Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("alumno", alumno);
            return "nuevo";
        }

        alumnoRepository.save(alumno);
        redirect.addFlashAttribute("msgExito", "El usuario fue guardado con exito");
        return "redirect:/";
    }

    @GetMapping(value = "/editar/{id}")
    public String formularioDeEditar(@PathVariable long id,Model model)
    {
        Alumnos alumno = alumnoRepository.findById(id).get();
        model.addAttribute("alumno", alumno);
        return "nuevo";
    }

    @PostMapping(value = "/editar/{id}")
    public String actualizarContacto(@PathVariable long id, @Valid Alumnos alumno, BindingResult bindingResult, RedirectAttributes redirect, Model model)
    {
        Alumnos alumnoDB = alumnoRepository.findById(id).get();
        if (bindingResult.hasErrors())
        {
            model.addAttribute("alumno", alumno);
            return "nuevo";
        }

        alumnoDB.setNombre(alumno.getNombre());
        alumnoDB.setApellido(alumno.getApellido());
        alumnoDB.setNota1(alumno.getNota1());
        alumnoDB.setNota2(alumno.getNota2());
        alumnoDB.setNota3(alumno.getNota3());
        alumnoRepository.save(alumno); // Corregir nombre de la variable
        redirect.addFlashAttribute("msgExito", "El usuario fue actualizado con exito");
        return "redirect:/";
    }

    @PostMapping(value = "/eliminar/{id}")
    public String eliminarAlumno(@PathVariable long id, RedirectAttributes redirect)
    {
        Alumnos alumno = alumnoRepository.findById(id).get();
        alumnoRepository.delete(alumno); // Corregir nombre de la variable
        redirect.addFlashAttribute("msgExito", "El usuario fue eliminado con exito");
        return "redirect:/";
    }



}
