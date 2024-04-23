package com.example.userposts.config;


import com.example.userposts.domain.Post;
import com.example.userposts.domain.User;
import com.example.userposts.dto.AuthorDto;
import com.example.userposts.dto.CommentDTO;
import com.example.userposts.repository.PostRepository;
import com.example.userposts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

@org.springframework.context.annotation.Configuration
public class Configuration implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        postRepository.deleteAll();

        var u1 = new User(null, "Carlos Neves", "carlos@hotmail.com");
        var u2 = new User(null, "Fatima da Silva", "fatima@hotmail.com");
        var u3 = new User(null, "Mário Lima", "mario@hotmail.com");
        userRepository.saveAll(Arrays.asList(u1, u2, u3));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        var p1 = new Post(null, sdf.parse("01/01/2024"), "Feliz ano novo!", "Um feliz ano novo a todos!", new AuthorDto(u1));
        var p2 = new Post(null, sdf.parse("15/02/2024"), "Viagem para Gramado", "Passando as férias em gramado...", new AuthorDto(u3));

        CommentDTO c1 = new CommentDTO("Feliz ano novo para você também!!!", sdf.parse("01/01/2024"), new AuthorDto(u2));
        CommentDTO c2 = new CommentDTO("Feliz ano novo Carlos!", sdf.parse("01/01/2024"), new AuthorDto(u3));
        CommentDTO c3 = new CommentDTO("Lugar muito bonito!", sdf.parse("17/02/2024"), new AuthorDto(u1));

        p1.getComments().addAll(Arrays.asList(c1, c2));
        p2.getComments().add(c3);

        postRepository.saveAll(Arrays.asList(p1, p2));

        u1.getPosts().add(p1);
        u3.getPosts().add(p2);
        userRepository.saveAll(Arrays.asList(u1, u3));
    }
}
