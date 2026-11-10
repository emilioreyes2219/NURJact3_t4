package com.edticsa.demoCrud.controllers;

import com.edticsa.demoCrud.models.Libro;
import com.edticsa.demoCrud.repository.AutorRepository;
import com.edticsa.demoCrud.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/libros")
public class LibroController {

    private final LibroRepository libroRepository;

    private final AutorRepository autorRepository;

    LibroController(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    @GetMapping
    public String listarLibros(Model model) {
        model.addAttribute("libros", libroRepository.findAll());
        return "libros/index";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("libro", new Libro());
        model.addAttribute("autores", autorRepository.findAll());
        return "libros/form";
    }

    @PostMapping("/guardar")
    public String guardarLibro(@ModelAttribute("libro") Libro libro) {
        libroRepository.save(libro);
        return "redirect:/libros";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Libro libro = libroRepository.findById(id).orElse(null);
        if (libro != null) {
            model.addAttribute("libro", libro);
            model.addAttribute("autores", autorRepository.findAll());
            return "libros/form";
        }
        return "redirect:/libros";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable Long id) {
        libroRepository.deleteById(id);
        return "redirect:/libros";
    }
}