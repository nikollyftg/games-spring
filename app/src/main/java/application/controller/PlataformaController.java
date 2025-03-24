package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import application.model.Plataforma;
import application.repository.PlataformaRepository;


@Controller
@RequestMapping("/plataforma") // Mapeamento base para todas as requisições deste controller
public class PlataformaController {

    @Autowired
    private PlataformaRepository plataformaRepo;

    @RequestMapping("/list")  // Para o método GET
    public String listPlataformas(Model ui) {
        ui.addAttribute("plataformas", plataformaRepo.findAll());
        return "plataforma/list"; // Nome da view para listar as plataformas
    }

    @RequestMapping("/insert")  // Para o método GET
    public String addPlataformaForm(Model ui) {
        return "plataforma/insert"; // Nome da view para o formulário de adição
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)  // Para o método POST
    public String insert(@RequestParam("nome") String nome) {
        Plataforma plataforma = new Plataforma();
        plataforma.setNome(nome);  // Defina o nome da plataforma com o valor recebido
        plataformaRepo.save(plataforma); // Corrigido para plataformaRepo
        return "redirect:/plataforma/list"; // Redireciona para a lista após adicionar
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)  // Para o método GET
    public String editPlataformaForm(@PathVariable("id") long id, Model model) {
        Plataforma plataforma = plataformaRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de plataforma inválido:" + id));
        model.addAttribute("plataforma", plataforma);
        return "plataforma/edit"; // Nome da view para o formulário de edição
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)  // Para o método POST
    public String updatePlataforma(@PathVariable("id") long id, @ModelAttribute Plataforma plataforma) {
        plataforma.setId(id); // Garante que estamos atualizando a plataforma correta
        plataformaRepo.save(plataforma); // Corrigido para plataformaRepo
        return "redirect:/plataforma/list"; // Redireciona para a lista após atualizar
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)  // Para o método GET
    public String deletePlataforma(@PathVariable("id") long id) {
        Plataforma plataforma = plataformaRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de plataforma inválido:" + id));
        plataformaRepo.delete(plataforma); // Corrigido para plataformaRepo
        return "redirect:/plataforma/list"; // Redireciona para a lista após excluir
    }
}