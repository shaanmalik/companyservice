package com.experian.companyservice;

import com.experian.companyservice.repository.CompanyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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

    @Autowired
    private CompanyRepository repository;

    // write test cases here

    @Test
    public void givenEmployees_whenGetEmployees_thenStatus200()
            throws Exception {

        createTestCompany(1,
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
                .andExpect(jsonPath("$[0].msg_id", is(1)));
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
}
