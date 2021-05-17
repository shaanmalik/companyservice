package com.experian.company;

import com.experian.company.repository.CompanyRepository;
import org.junit.Before;
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
        classes = CompanyApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class CompanyControllerIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CompanyRepository repository;

    @Before
    public void before() {
        repository.deleteAll();
    }

    @Test
    public void createTestCompany()
            throws Exception {

        createTestCompany(1,
                "acme company",
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
                .andExpect(jsonPath("$[0].company_name", is("acme company")))
                .andExpect(jsonPath("$[0].msg_id", is(1)));
    }

    @Test
    public void updatingExistingCompanyUpdatesRecord()
            throws Exception {

        createTestCompany(1,
                "acme company",
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
                .andExpect(jsonPath("$[0].company_name", is("acme company")))
                .andExpect(jsonPath("$[0].msg_id", is(1)));

        createTestCompany(1,
                "acme company2",
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
                .andExpect(jsonPath("$[0].company_name", is("acme company2")))
                .andExpect(jsonPath("$[0].msg_id", is(1)));
    }

    @Test
    public void createTwoCompanies()
            throws Exception {

        createTestCompany(1,
                "acme company",
                "2020-10-27T14:34:06.132Z",
                3.2f,
                5,
                "2020-12-27T14:34:06.132Z"
        );

        createTestCompany(2,
                "abcd company",
                "2020-10-27T14:34:06.132Z",
                2.3f,
                7,
                "2020-12-27T14:34:06.132Z"
        );

        mvc.perform(get("/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].msg_id", is(1)))
                .andExpect(jsonPath("$[0].company_name", is("acme company")))
                .andExpect(jsonPath("$[1].msg_id", is(2)))
                .andExpect(jsonPath("$[1].company_name", is("abcd company")));
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
