package com.edticsa.demoCrud.controllers;

import com.edticsa.demoCrud.models.Autor;
import com.edticsa.demoCrud.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/autores")
public class AutorController {

    private final AutorRepository autorRepository;

    AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @GetMapping
    public String listarAutores(Model model) {
        model.addAttribute("autores", autorRepository.findAll());
        return "autores/index";
    }

    @GetMapping("/nuevo")
    public String nuevoAutor(Model model) {
        model.addAttribute("autor", new Autor());
        return "autores/form";
    }

    @PostMapping("/guardar")
    public String guardarAutor(@ModelAttribute("autor") Autor autor) {
        autorRepository.save(autor);
        return "redirect:/autores";
    }
}