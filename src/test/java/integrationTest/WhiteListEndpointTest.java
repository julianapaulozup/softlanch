package integrationTest;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import softLaunch.SoftLaunchMain;
import softLaunch.domain.Response;
import softLaunch.repository.WhiteListRepository;
import softLaunch.domain.RequestWrapper;
import softLaunch.domain.WhiteList;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SoftLaunchMain.class)
@AutoConfigureMockMvc
public class WhiteListEndpointTest {
    
    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private WhiteListRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listWhiteListsShouldReturnStatusCode200(){
        List<WhiteList> whiteLists = asList(new WhiteList(1L,"White List","10"));
        BDDMockito.when(repository.findAll()).thenReturn(whiteLists);
        ResponseEntity<String> response = restTemplate.getForEntity("/whitelist/", String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void createWhiteListtShouldReturnStatusCode201() {
        List<Response> list = new ArrayList<>();
        list.add(new Response("name","cpf"));
        RequestWrapper whiteList = new RequestWrapper(list);
        ResponseEntity<RequestWrapper> responseEntity =
                restTemplate.postForEntity("/whitelist/batch", whiteList, RequestWrapper.class);
        RequestWrapper wrapper = responseEntity.getBody();

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(whiteList.getWhiteLists().size(),wrapper.getWhiteLists().size());
    }

}

