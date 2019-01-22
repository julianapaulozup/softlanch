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
import softLaunch.service.whitelist.WhiteList;
import softLaunch.service.whitelist.WhiteListService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(WhiteListController.class)
@AutoConfigureDataJpa
public class WhiteListControllerTest {

        @Autowired
        private MockMvc mvc;

        @MockBean
        private WhiteListService service;

        @Rule
        public ExpectedException thrown = ExpectedException.none();

        @Test
        public void givenWhiteLists_whenGetWhiteLists_thenReturnJsonArray() throws Exception {

            WhiteList whitelist = new WhiteList(11L,"Nome","1111111");

            List<WhiteList> allWhiteLists = Arrays.asList(whitelist);

            given(service.getAllWhiteLists()).willReturn(allWhiteLists);

            mvc.perform(get("/whitelist/")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].name").value("Nome"));
        }

        @Test
        public void whenpostWhiteList_thenReturnCreated() throws Exception {


            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "Whitelist");
            jsonObject.put("cpf", "111111");

            JSONArray whiteLists= new JSONArray();
            whiteLists.put(jsonObject);

            org.json.JSONObject main = new org.json.JSONObject();
            main.put("whiteLists",whiteLists);

            mvc.perform(post("/whitelist/batch")
                    .contentType("application/json;charset=UTF-8")
                    .content(String.valueOf(main)))
                    .andDo(print())
                    .andExpect(status().isCreated());
        }

        @Test
        public void whendeleteWhiteList_thenReturnOk() throws Exception {

            mvc.perform(delete("/whitelist/"))
                    .andExpect(status().isOk());
        }

}
