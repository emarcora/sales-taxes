package com.emarcora.service.api;

import com.emarcora.Application;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
public class ApplyTaxesServiceIntegrationTest {

    @Autowired
    private MockMvc mvc;

    static String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    @Test
    void test1() throws Exception {
        String file = "src/test/resources/input1.json";
        String request = readFileAsString(file);

        mvc.perform(post("/applyTaxes")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sales_taxes", is(1.50)))
                .andExpect(jsonPath("$.total_amount", is(29.83)))
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()));
    }

    @Test
    void test2() throws Exception {
        String file = "src/test/resources/input2.json";
        String request = readFileAsString(file);

        mvc.perform(post("/applyTaxes")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sales_taxes", is(7.65)))
                .andExpect(jsonPath("$.total_amount", is(65.15)))
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()));
    }

    @Test
    void test3() throws Exception {
        String file = "src/test/resources/input3.json";
        String request = readFileAsString(file);

        mvc.perform(post("/applyTaxes")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sales_taxes", is(6.70)))
                .andExpect(jsonPath("$.total_amount", is(74.68)))
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()));
    }

}
