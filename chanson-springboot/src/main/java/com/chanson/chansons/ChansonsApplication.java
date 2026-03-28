package com.chanson.chansons;

import com.chanson.chansons.model.Chanson;
import com.chanson.chansons.model.Genre;
import com.chanson.chansons.model.Role;
import com.chanson.chansons.model.User;
import com.chanson.chansons.repos.GenreRepository;
import com.chanson.chansons.service.ChansonService;
import com.chanson.chansons.service.UserService;
import com.chanson.chansons.dto.ChansonDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class ChansonsApplication implements CommandLineRunner {

    @Autowired
    ChansonService chansonService;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(ChansonsApplication.class, args);
    }

    /*
    @PostConstruct
    void init_users() {
        userService.addRole(new Role(null, "ADMIN"));
        userService.addRole(new Role(null, "AGENT"));
        userService.addRole(new Role(null, "USER"));

        userService.saveUser(new User(null, "admin",  "123", true, new ArrayList<>()));
        userService.saveUser(new User(null, "agent1", "123", true, new ArrayList<>()));
        userService.saveUser(new User(null, "user1",  "123", true, new ArrayList<>()));

        userService.addRoleToUser("admin",  "ADMIN");
        userService.addRoleToUser("agent1", "AGENT");
        userService.addRoleToUser("agent1", "USER");
        userService.addRoleToUser("user1",  "USER");
    }
    */

    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Chanson.class);

        /*
        Genre pop  = genreRepository.save(new Genre(null, "Pop",  "Musique populaire", null));
        Genre rock = genreRepository.save(new Genre(null, "Rock", "Musique rock", null));
        Genre jazz = genreRepository.save(new Genre(null, "Jazz", "Musique jazz", null));
        Genre rap  = genreRepository.save(new Genre(null, "Rap",  "Musique rap", null));

        chansonService.saveChanson(new ChansonDTO(null, "Bohemian Rhapsody",     "Queen",          354, new Date(), rock, "Rock"));
        chansonService.saveChanson(new ChansonDTO(null, "Shape of You",          "Ed Sheeran",     234, new Date(), pop,  "Pop"));
        chansonService.saveChanson(new ChansonDTO(null, "Blinding Lights",       "The Weeknd",     200, new Date(), pop,  "Pop"));
        chansonService.saveChanson(new ChansonDTO(null, "Hotel California",      "Eagles",         391, new Date(), rock, "Rock"));
        chansonService.saveChanson(new ChansonDTO(null, "So What",               "Miles Davis",    562, new Date(), jazz, "Jazz"));
        chansonService.saveChanson(new ChansonDTO(null, "Lose Yourself",         "Eminem",         326, new Date(), rap,  "Rap"));
        chansonService.saveChanson(new ChansonDTO(null, "Billie Jean",           "Michael Jackson",294, new Date(), pop,  "Pop"));
        chansonService.saveChanson(new ChansonDTO(null, "Smells Like Teen Spirit","Nirvana",       301, new Date(), rock, "Rock"));
        */
    }
}