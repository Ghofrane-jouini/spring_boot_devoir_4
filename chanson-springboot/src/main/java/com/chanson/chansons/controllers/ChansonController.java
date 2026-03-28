package com.chanson.chansons.controllers;

import com.chanson.chansons.dto.ChansonDTO;
import com.chanson.chansons.model.Genre;
import com.chanson.chansons.service.ChansonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class ChansonController {

    @Autowired
    ChansonService chansonService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(java.util.Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                try {
                    if (text == null || text.trim().isEmpty()) {
                        setValue(null);
                    } else {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        sdf.setLenient(false);
                        setValue(sdf.parse(text.trim()));
                    }
                } catch (Exception e) {
                    setValue(null);
                }
            }
        });
    }

    @GetMapping("/")
    public String welcome() {
        return "index";
    }

    @RequestMapping("/ListeChansons")
    public String listeChansons(ModelMap modelMap,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "5") int size) {

        Page<ChansonDTO> chansons = chansonService.getAllChansonsParPage(page, size);

        modelMap.addAttribute("chansons", chansons); // ✅ corrigé
        modelMap.addAttribute("pages", new int[chansons.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);

        addSecurityAttributes(modelMap);

        return "listeChansons";
    }

    @RequestMapping("/showCreate")
    public String showCreate(ModelMap modelMap) {
        List<Genre> genres = chansonService.getAllGenres();

        modelMap.addAttribute("Chanson", new ChansonDTO());
        modelMap.addAttribute("mode", "new");
        modelMap.addAttribute("Genres", genres);

        addSecurityAttributes(modelMap);

        return "formChanson";
    }

    @RequestMapping("/saveChanson")
    public String saveChanson(@Valid @ModelAttribute("Chanson") ChansonDTO chansonDTO,
                              BindingResult bindingResult,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "5") int size,
                              ModelMap modelMap) {

        if (bindingResult.hasErrors()) {
            List<Genre> genres = chansonService.getAllGenres();

            modelMap.addAttribute("Chanson", chansonDTO);
            modelMap.addAttribute("Genres", genres);
            modelMap.addAttribute("mode", "new");

            return "formChanson";
        }

        chansonService.saveChanson(chansonDTO);

        return "redirect:/ListeChansons?page=" + page + "&size=" + size;
    }

    @RequestMapping("/supprimerChanson")
    public String supprimerChanson(@RequestParam("id") Long id,
                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "5") int size,
                                   ModelMap modelMap) {

        chansonService.deleteChansonById(id);

        Page<ChansonDTO> chansons = chansonService.getAllChansonsParPage(page, size);

        modelMap.addAttribute("chansons", chansons); // ✅ corrigé
        modelMap.addAttribute("pages", new int[chansons.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);

        return "listeChansons";
    }

    private void addSecurityAttributes(ModelMap modelMap) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"));

        boolean isAgent = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("AGENT"));

        modelMap.addAttribute("isAdmin", isAdmin);
        modelMap.addAttribute("isAgent", isAgent);
        modelMap.addAttribute("username", auth.getName());
    }
}