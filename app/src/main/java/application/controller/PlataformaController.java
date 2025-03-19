package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "plataforma/list"; // Certifique-se de que a view exista
    }

    @GetMapping("/insert")
    public String addPlataformaForm(Model ui) {
        return "plataforma/insert"; // Certifique-se de que a view exista
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestParam("nome") String nome) {
        Plataforma plataforma = new Plataforma();
        plataforma.setNome(nome);
        plataformaRepo.save(plataforma);
        return "redirect:/plataforma/list"; // Ajustando o caminho
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") long id, Model model) {
        Plataforma plataforma = plataformaRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de plataforma inválido: " + id));
        model.addAttribute("plataforma", plataforma);
        return "plataforma/update"; // Ajustando o nome da pasta
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @ModelAttribute Plataforma plataforma) {
        plataforma.setId(id); // Garante que estamos atualizando a plataforma correta
        plataformaRepo.save(plataforma);
        return "redirect:/plataforma/list"; // Ajuste do caminho de redirecionamento
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        Plataforma plataforma = plataformaRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de plataforma inválido: " + id));
        plataformaRepo.delete(plataforma);
        return "redirect:/plataforma/list"; // Ajuste do caminho de redirecionamento
    }
}
