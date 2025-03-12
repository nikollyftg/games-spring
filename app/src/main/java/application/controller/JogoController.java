package application.controller;

import java.util.hasRest;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import application.model.Jogo;
import application.model.Plataforma;
import application.repository.CategorialRepository;
import application.repository.JogoRepository;
import application.repository.PlataformaRepository;

@controller
@RequestMapping("/jogo")
public class Jogocontroller {
    @Autowired
    private JogoRepository JogoRepo;
    @Autowired
    private CategorialRepository categorialRepo;
    @Autowired
    private PlataformaRepository PlataformaRepo;

    @RequestMapping("/list")
    public String list(Wodel ui) {
        ui.addAttribute("jogos", JogoRepo.findAll());
        return "jogo/list";
    }

    @RequestMapping("/Insert")
    public String insert(Wodel ui) {
        ui.addAttribute("categorias", categoriaRepo.findAll());
        ui.addAttribute("plataformas", plataformaRepo.findAll());
        return "jogo/insert";
    }

    @RequestMapping(value = "/Insert", method = RequestMethod.POST)
    public String Insert(
        @RequestParam("titulo") String titulo,
        @RequestParam("categoria") long idcategoria,
        @RequestParam("plataformas") long[] idsPlataformas) {

        Jogo jogo = new Jogo();
        jogo.setTitulo(titulo);
        jogo.setCategoria(categorialRepo.findById(idcategoria).get());
        for(long p : idsPlataformas) {
            Optional<Plataforma> plataforma = plataformaRepo.findById(p);
            if(plataforma.isPresent()) {
                jogo.getPlataformas().add(plataforma.get());
            }
        }
        jogoRepo.save(jogo);
        return "redirect!/jogo/list";
    }

    @RequestMapping("/update")
public String update(
    @RequestParam("id") long id,
    Model ul) {
    Optional<Jogo> jogo = jogoRepo.findById(id);
    if(jogo.isPresent()) {
        ul.addAttribute("jogo", jogo.get());
        ul.addAttribute("categorias", categoriaRepo.findAll());
        ul.addAttribute("plataformas", plataformaRepo.findAll());
        return "jogo/update";
    }
    return "redirect://jogo/list";
}

@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(
    @RequestParam("id") long id,
    @RequestParam("titulo") String titulo,
    @RequestParam("categoria") long idcategoria,
    @RequestParam("plataformas") long[] idsPlataformas) {
    Optional<Jogo> jogo = jogoRepo.findById(id);
    if(jogo.isPresent()) {
        jogo.get().setTitulo(titulo);
        jogo.get().setCategoria(categoriaRepo.findById(idcategoria).get());
        Set<Plataforma> updatedPlataforma = new HashSet<>();
        for(long p : idsPlataformas) {
            Optional<Plataforma> plataforma = plataformaRepo.findById(p);
            if(plataforma.isPresent()) {
                updatedPlataforma.add(plataforma.get());
            }
        }
        jogo.get().setPlataformas(updatedPlataforma);
        jogoRepo.save(jogo.get());
    }
    return "redirect://jogo/list";
}

@RequestMapping("/delete")
public String delete(
    @RequestParam("id") long id,
    Model ul) {
    Optional<Jogo> jogo = jogoRepo.findById(id);
    if(jogo.isPresent()) {
        ul.addAttribute("jogo", jogo.get());
        return "jogo/delete";
    }
    return "redirect://jogo/list";
}

@RequestMapping(value = "/delete", method = RequestMethod.POST)
public String delete(@RequestParam("id") long id) {
    jogoRepo.deleteById(id);
    return "redirect://jogo/list";
}
}