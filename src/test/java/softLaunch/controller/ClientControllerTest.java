package softLaunch.controller;

import org.json.JSONObject;
import org.json.JSONArray;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import softLaunch.service.AttemptService;
import softLaunch.domain.Client;
import softLaunch.service.ClientService;
import softLaunch.service.WhiteListService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientController.class)
@AutoConfigureDataJpa
public class ClientControllerTest {

        @Autowired
        private MockMvc mvc;

        @MockBean
        private ClientService service;

        @MockBean
        private WhiteListService whiteListService;

        @MockBean
        private AttemptService attemptService;

        @Rule
        public ExpectedException thrown = ExpectedException.none();

        @Test
        public void givenClients_whenGetClients_thenReturnJsonArray() throws Exception {

            Client client = new Client(11L,"Nome","111111");

            List<Client> allClients = Arrays.asList(client);

            given(service.getAllClients()).willReturn(allClients);

            mvc.perform(get("/client/")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].name").value("Nome"));
        }

        @Test
        public void whenpostClient_thenReturnCreated() throws Exception {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "Client");
            jsonObject.put("cpf", "11111");

            JSONArray whiteLists= new JSONArray();
            whiteLists.put(jsonObject);

            JSONObject main = new JSONObject();
            main.put("whiteLists",whiteLists);

            System.out.println(whiteLists);

            mvc.perform(post("/client/batch")
                    .contentType("application/json;charset=UTF-8")
                    .content(String.valueOf(main)))
                    .andDo(print())
                    .andExpect(status().isCreated());
        }

}


