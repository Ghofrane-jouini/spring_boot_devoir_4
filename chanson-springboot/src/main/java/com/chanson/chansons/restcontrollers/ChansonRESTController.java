package com.chanson.chansons.restcontrollers;

import com.chanson.chansons.dto.ChansonDTO;
import com.chanson.chansons.service.ChansonService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ChansonRESTController {

    private final ChansonService chansonService;

    public ChansonRESTController(ChansonService chansonService) {
        this.chansonService = chansonService;
    }

    @GetMapping
    public List<ChansonDTO> getAllChansons() {
        return chansonService.getAllChansons();
    }

    @GetMapping("/page")
    public Page<ChansonDTO> getAllChansonsByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return chansonService.getAllChansonsParPage(page, size);
    }

    @GetMapping("/{id}")
    public ChansonDTO getChansonById(@PathVariable Long id) {
        return chansonService.getChanson(id);
    }

    @PostMapping
    public ChansonDTO createChanson(@RequestBody ChansonDTO chanson) {
        return chansonService.saveChanson(chanson);
    }

    @PutMapping
    public ChansonDTO updateChanson(@RequestBody ChansonDTO chanson) {
        return chansonService.updateChanson(chanson);
    }

    @DeleteMapping("/{id}")
    public void deleteChanson(@PathVariable Long id) {
        chansonService.deleteChansonById(id);
    }

    @GetMapping("/chansonsgenre/{idGenre}")
    public List<ChansonDTO> getChansonsByGenreId(@PathVariable Long idGenre) {
        return chansonService.findByGenreIdGenre(idGenre);
    }

    @GetMapping("/search/titre")
    public List<ChansonDTO> searchByTitre(@RequestParam String titre) {
        return chansonService.findByTitreChansonContains(titre);
    }

    @GetMapping("/search/artiste")
    public List<ChansonDTO> searchByArtiste(@RequestParam String artiste) {
        return chansonService.findByArtisteContains(artiste);
    }
}
