package com.experian.companyservice;

import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Random;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CompanyserviceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class CompanyControllerIntegrationTests {

    @Autowired
    private MockMvc mvc;

    private Random random = new Random();

    @Test
    public void createTestCompany()
            throws Exception {

        int id = generateId();

        createTestCompany(id,
                "bob's geldofs",
                "2020-10-27T14:34:06.132Z",
        3.2f,
                5,
                "2020-12-27T14:34:06.132Z"
        );

        mvc.perform(get("/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0].company_name", is("bob's geldofs")))
                .andExpect(jsonPath("$[0].msg_id", is(id)));
    }

    @Test
    public void updatingExistingCompanyUpdatesRecord()
            throws Exception {

        int id = generateId();

        createTestCompany(id,
                "bob's geldofs",
                "2020-10-27T14:34:06.132Z",
                3.2f,
                5,
                "2020-12-27T14:34:06.132Z"
        );

        mvc.perform(get("/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].company_name", is("bob's geldofs")))
                .andExpect(jsonPath("$[0].msg_id", is(id)));

        createTestCompany(id,
                "bob's geldofs2",
                "2020-10-27T14:34:06.132Z",
                5.2f,
                5,
                "2020-12-27T14:34:06.132Z"
        );

        mvc.perform(get("/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].company_name", is("bob's geldofs2")))
                .andExpect(jsonPath("$[0].msg_id", is(id)));
    }

    @Test
    public void createTwoCompanies()
            throws Exception {

        int id1 = generateId();
        int id2 = generateId();

        createTestCompany(id1,
                "bob's geldofs",
                "2020-10-27T14:34:06.132Z",
                3.2f,
                5,
                "2020-12-27T14:34:06.132Z"
        );

        createTestCompany(id2,
                "dave's hasselhoffs",
                "2020-10-27T14:34:06.132Z",
                2.3f,
                7,
                "2020-12-27T14:34:06.132Z"
        );

        MvcResult result = mvc.perform(get("/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].company_name", is("bob's geldofs")))
                .andExpect(jsonPath("$[0].msg_id", is(id1)))
        .andReturn();
        String content = result.getResponse().getContentAsString();
        JsonPath.parse(content);
    }

    private void createTestCompany(int msg_id,
                                   String company_name,
                                   String registration_date,
                                   float score,
                                   int directors_count,
                                   String last_updated
                                   ) throws Exception {
        mvc.perform(post("/")
                .content("{\"msg_id\": " + msg_id + ", " +
                        "\"company_name\":\"" + company_name + "\"," +
                        "\"registration_date\": \"" + registration_date + "\"," +
                        "\"score\":" + score + "," +
                        "\"directors_count\":" + directors_count + "," +
                        "\"last_updated\": \"" + last_updated + "\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private int generateId() {
        return random.nextInt(5000);
    }



}
