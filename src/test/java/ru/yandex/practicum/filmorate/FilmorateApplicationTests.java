package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.yandex.practicum.filmorate.controller.UserController;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FilmorateApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @Test
    public void test1_createUserSuccessful() throws Exception {
        String jsonString = "{\"email\":\"email@mail.ru\"," +
                "\"login\":\"login\"," +
                "\"name\":\"name\"," +
                "\"birthday\":\"2020-02-02\"}";
        this.mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void test2_createUserIfNullEmail() throws Exception {
        String badEmail1 = "{\"email\":\"\"," +
                "\"login\":\"login\"," +
                "\"name\":\"name\"," +
                "\"birthday\":\"2020-02-02\"}";
        this.mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(badEmail1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void test3_createUserIfBadEmail() throws Exception {
        String badEmail1 = "{\"email\":\"emailmail.ru\"," +
                "\"login\":\"login\"," +
                "\"name\":\"name\"," +
                "\"birthday\":\"2020-02-02\"}";
        this.mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(badEmail1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void test4_createUserIfNullLogin() throws Exception {
        String badLogin = "{\"email\":\"email@mail.ru\"," +
                "\"login\":\"\"," +
                "\"name\":\"name\"," +
                "\"birthday\":\"2020-02-02\"}";
        this.mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(badLogin)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void test5_createUserIfBlankLogin() throws Exception {
        String badLogin = "{\"email\":\"email@mail.ru\"," +
                "\"login\":\" \"," +
                "\"name\":\"name\"," +
                "\"birthday\":\"2020-02-02\"}";
        this.mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(badLogin)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void test6_createUserIfBlankName() throws Exception {
        String successEmail = "{\"email\":\"email@mail.ru\"," +
                "\"login\":\"login\"," +
                "\"name\":\"\"," +
                "\"birthday\":\"2020-02-02\"}";
        this.mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(successEmail)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }


    @Test
    public void test7_createUserClientErrorIfBadBirthday() throws Exception {
        String badLogin = "{\"email\":\"email@mail.ru\"," +
                "\"login\":\"login\"," +
                "\"name\":\"name\"," +
                "\"birthday\":\"9999-02-02\"}";
        this.mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(badLogin)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void test8_createFilmSuccessful() throws Exception {
        String successFilm = "{\"name\":\"name\"," +
                "\"description\":\"login\"," +
                "\"releaseDate\":\"2020-02-02\"," +
                "\"duration\":\"15\"}";
        this.mockMvc.perform(MockMvcRequestBuilders.post("/films")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(successFilm)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void test9_createFilmErrorIfNullName() throws Exception {
        String badFilm = "{\"name\":\"\"," +
                "\"description\":\"login\"," +
                "\"releaseDate\":\"2020-02-02\"," +
                "\"duration\":\"15\"}";
        this.mockMvc.perform(MockMvcRequestBuilders.post("/films")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(badFilm)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void test10_createFilmErrorIfMaxLength() throws Exception {
        String badFilm = "{\"id\":\"1\", \"name\":\"\"," +
                "\"description\":\"loginlogiloginlogiloginlloginoginloginloginnloginloginloginloginloginlogin" +
                "loginloginloginloginloginloginloginlloginloginloginloginoginloginloginloginloginloginnlogin\"," +
                "\"releaseDate\":\"2020-02-02\"," +
                "\"duration\":\"15\"}";
        this.mockMvc.perform(MockMvcRequestBuilders.post("/films")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(badFilm)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void test11_createFilmErrorIfBadReleaseDate() throws Exception {
        String badFilm = "{\"name\":\"asd\"," +
                "\"description\":\"login\"," +
                "\"releaseDate\":\"1890-03-25\"," +
                "\"duration\":\"15\"}";
        this.mockMvc.perform(MockMvcRequestBuilders.post("/films")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(badFilm)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void test12_createFilmErrorIfBadDuration() throws Exception {
        String badFilm = "{\"name\":\"asd\"," +
                "\"description\":\"login\"," +
                "\"releaseDate\":\"2020-12-28\"," +
                "\"duration\":\"-30\"}";
        this.mockMvc.perform(MockMvcRequestBuilders.post("/films")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(badFilm)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

}
