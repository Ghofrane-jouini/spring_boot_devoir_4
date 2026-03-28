package com.chanson.chansons;

import com.chanson.chansons.model.Chanson;
import com.chanson.chansons.model.Genre;
import com.chanson.chansons.repos.ChansonRepository;
import com.chanson.chansons.repos.GenreRepository;
import com.chanson.chansons.service.ChansonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class ChansonsApplicationTests {

    @Autowired
    private ChansonRepository chansonRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ChansonService chansonService;

    @Test
    public void testCreateChanson() {
        Chanson c = new Chanson("Imagine", "John Lennon", 187, new Date());
        chansonRepository.save(c);
        System.out.println("Chanson créée : " + c);
    }

    @Test
    public void testFindChanson() {
        Chanson c = chansonRepository.findById(1L).get();
        System.out.println("Chanson trouvée : " + c);
    }

    @Test
    public void testUpdateChanson() {
        Chanson c = chansonRepository.findById(1L).get();
        c.setDureeSecondes(200);
        chansonRepository.save(c);
        System.out.println("Chanson mise à jour : " + c);
    }

    @Test
    public void testDeleteChanson() {
        chansonRepository.deleteById(1L);
        System.out.println("Chanson supprimée.");
    }

    @Test
    public void testListerToutesChansons() {
        List<Chanson> chansons = chansonRepository.findAll();
        for (Chanson c : chansons) {
            System.out.println(c);
        }
    }

    @Test
    public void testFindByTitreChanson() {
        List<Chanson> chansons = chansonRepository.findByTitreChanson("Imagine");
        for (Chanson c : chansons) {
            System.out.println(c);
        }
    }

    @Test
    public void testFindByTitreChansonContains() {
        List<Chanson> chansons = chansonRepository.findByTitreChansonContains("Im");
        for (Chanson c : chansons) {
            System.out.println(c);
        }
    }

    @Test
    public void testFindByArtiste() {
        List<Chanson> chansons = chansonRepository.findByArtiste("Queen");
        for (Chanson c : chansons) {
            System.out.println(c);
        }
    }

    @Test
    public void testFindByArtisteContains() {
        List<Chanson> chansons = chansonRepository.findByArtisteContains("Qu");
        for (Chanson c : chansons) {
            System.out.println(c);
        }
    }

    @Test
    public void testFindByTitreDuree() {
        List<Chanson> chansons = chansonRepository.findByTitreDuree("Bohemian", 200);
        for (Chanson c : chansons) {
            System.out.println(c);
        }
    }

    @Test
    public void testFindByGenreIdGenre() {
        List<Chanson> chansons = chansonRepository.findByGenreIdGenre(1L);
        for (Chanson c : chansons) {
            System.out.println(c);
        }
    }

    @Test
    public void testFindByOrderByTitreChansonAsc() {
        List<Chanson> chansons = chansonRepository.findByOrderByTitreChansonAsc();
        for (Chanson c : chansons) {
            System.out.println(c);
        }
    }

    @Test
    public void testTrierChansonsTitreDuree() {
        List<Chanson> chansons = chansonRepository.trierChansonsTitreDuree();
        for (Chanson c : chansons) {
            System.out.println(c);
        }
    }
}
