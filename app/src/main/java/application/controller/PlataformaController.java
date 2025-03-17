package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        return "plataforma/list"; // Nome correto da view
    }

    @GetMapping("/insert")
    public String addPlataformaForm() {
        return "plataforma/insert"; // Nome correto da view
    }

    @PostMapping("/insert")
    public String insert(@RequestParam("nome") String nome) {
        Plataforma plataforma = new Plataforma(); // Criando um novo objeto
        plataforma.setNome(nome);
        plataformaRepo.save(plataforma);
        return "redirect:/plataforma/list"; // Nome correto do redirecionamento
    }

    @GetMapping("/edit/{id}")
    public String editPlataformaForm(@PathVariable("id") long id, Model model) {
        Plataforma plataforma = plataformaRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de plataforma inválido: " + id));
        model.addAttribute("plataforma", plataforma);
        return "plataforma/edit"; // Nome correto da view
    }

    @PostMapping("/update/{id}")
    public String updatePlataforma(@PathVariable("id") long id, @ModelAttribute Plataforma plataforma) {
        plataforma.setId(id);
        plataformaRepo.save(plataforma);
        return "redirect:/plataforma/list"; // Nome correto do redirecionamento
    }

    @GetMapping("/delete/{id}")
    public String deletePlataforma(@PathVariable("id") long id) {
        Plataforma plataforma = plataformaRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de plataforma inválido: " + id));
        plataformaRepo.delete(plataforma);
        return "redirect:/plataforma/list"; // Nome correto do redirecionamento
    }
}
