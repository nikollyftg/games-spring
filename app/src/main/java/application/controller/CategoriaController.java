package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import application.model.Categoria;
import application.repository.CategoriaRepository;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepo;

    @GetMapping("/list")
    public String list(Model ui) {
        ui.addAttribute("categorias", categoriaRepo.findAll());
        return "categoria/list";
    }

    @GetMapping("/insert")
    public String insert() {
        return "categoria/insert";
    }

    @PostMapping("/insert")
    public String insert(@RequestParam("nome") String nome) {
        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        categoriaRepo.save(categoria);
        return "redirect:/categoria/list";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") long id, Model ui) {
        Categoria categoria = categoriaRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada!"));
        ui.addAttribute("categoria", categoria);
        return "categoria/update";
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") long id, @RequestParam("nome") String nome) {
        Categoria categoria = categoriaRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada!"));
        categoria.setNome(nome);
        categoriaRepo.save(categoria);
        return "redirect:/categoria/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") long id, Model ui) {
        Categoria categoria = categoriaRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada!"));
        ui.addAttribute("categoria", categoria);
        return "categoria/delete";
    }

    @PostMapping("/delete")
    public String deleteConfirm(@RequestParam("id") long id) {
        if (categoriaRepo.existsById(id)) {
            categoriaRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException("Categoria não encontrada!");
        }
        return "redirect:/categoria/list";
    }
}
