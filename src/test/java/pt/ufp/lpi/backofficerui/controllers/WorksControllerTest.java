package pt.ufp.lpi.backofficerui.controllers;

import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.lpi.backofficerui.dao.WorksDAO;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(WorksController.class)
@AutoConfigureRestDocs(outputDir = "src/main/asciidoc/snippets")

class WorksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WorksDAO worksDAO;

    @SneakyThrows
    @Test
    void getWorks() {

        when(worksDAO.getAllWorks()).thenReturn(new JSONArray("[{hello:world}]"));

        this.mockMvc.perform(get("/works")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("[{\"hello\":\"world\"}]"))
                .andDo(document("all-works"));
    }

    @SneakyThrows
    @Test
    void getWorkByTitle() {

        when(worksDAO.getWorkByTitle("hello")).thenReturn(Optional.of(new JSONObject("{hello:world}")));

        this.mockMvc.perform(get("/works/title/hello")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("{\"hello\":\"world\"}"))
                .andDo(document("get-work"));
    }

    @Test
    void replaceAllWorks() {
    }
}