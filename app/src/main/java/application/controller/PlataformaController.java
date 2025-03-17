package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import application.model.Plataforma;
import application.repository.PlataformaRepository;

@Controller
@RequestMapping("/plataforma")
public class PlataformaController {

    @Autowired
    private PlataformaRepository plataformaRepo;

    @GetMapping("/list")
    public String listPlataformas(Model ui) {
        ui.addAttribute("plataformas", plataformaRepo.findAll());
        return "plataforma/list";
    }

    @GetMapping("/insert")
    public String addPlataformaForm() {
        return "plataforma/insert";
    }

    @PostMapping("/insert")
    public String insert(@RequestParam("nome") String nome) {
        Plataforma plataforma = new Plataforma();
        plataforma.setNome(nome);
        plataformaRepo.save(plataforma);
        return "redirect:/plataforma/list";
    }

    @GetMapping("/edit/{id}")
    public String editPlataformaForm(@PathVariable("id") long id, Model model) {
        Plataforma plataforma = plataformaRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de plataforma inválido: " + id));
        model.addAttribute("plataforma", plataforma);
        return "plataforma/edit";
    }

    @PostMapping("/update/{id}")
    public String updatePlataforma(@PathVariable("id") long id, @ModelAttribute Plataforma plataforma) {
        if (plataformaRepo.existsById(id)) {
            plataforma.setId(id);
            plataformaRepo.save(plataforma);
        } else {
            throw new IllegalArgumentException("ID de plataforma inválido: " + id);
        }
        return "redirect:/plataforma/list";
    }

    @GetMapping("/delete/{id}")
    public String deletePlataforma(@PathVariable("id") long id) {
        if (plataformaRepo.existsById(id)) {
            plataformaRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException("ID de plataforma inválido: " + id);
        }
        return "redirect:/plataforma/list";
    }
}
