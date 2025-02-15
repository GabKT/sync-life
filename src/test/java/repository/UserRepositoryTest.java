package repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gabkt.SyncLife;
import com.gabkt.model.Usuario;
import com.gabkt.repository.UsuarioRepository;

@SpringBootTest(classes = SyncLife.class)
public class UserRepositoryTest {
    @Autowired
    UsuarioRepository usuarioRepository;

    @BeforeEach
    void setup() {
        Usuario usuarioPadrao = new Usuario("Gabriel", "teste@email.com", "19991587201", "12345678");
        usuarioRepository.salvarUsuario(usuarioPadrao);
    }

    @AfterEach
    void endSetup() {
        Usuario usuario = usuarioRepository.buscarUsuarioPorEmail("teste@email.com");
        if (usuario != null) {
            usuarioRepository.deletarUsuario(usuario);
        }
    }

    @Test
    void deveVisualizarUsuario() {
        Usuario usuario = usuarioRepository.buscarUsuarioPorEmail("teste@email.com");

        assertEquals("Gabriel", usuario.getNome());
        assertEquals("19991587201", usuario.getTelefone());
    }

    @Test
    void deveAtualizarUsuario() {
        Usuario u1 = usuarioRepository.buscarUsuarioPorEmail("teste@email.com");
        u1.setNome("Gustavo");
        u1.setEmail("example@teste.com");
        u1.setTelefone("19991919292");

        usuarioRepository.atualizarUsuario(u1);

        Usuario u2 = usuarioRepository.buscarUsuarioPorEmail("example@teste.com");
        System.out.println(u2);
        if (u2.getEmail().equals("example@teste.com")) {
            usuarioRepository.deletarUsuario(u2);
        }
        assertEquals(u1, u2);
        assertEquals("Gustavo", u2.getNome());
        assertEquals("example@teste.com", u2.getEmail());
        assertEquals("19991919292", u2.getTelefone());
    }

    @Test
    void deveDeletarUsuario() {
        Usuario u1 = usuarioRepository.buscarUsuarioPorEmail("teste@email.com");
        usuarioRepository.deletarUsuario(u1);
        Usuario u2 = usuarioRepository.buscarUsuarioPorEmail("teste@email.com");
        assertNull(u2);
    }
}
