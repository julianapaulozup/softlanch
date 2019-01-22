package softLaunch.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import softLaunch.repository.WhiteListRepository;
import softLaunch.service.whitelist.WhiteList;
import softLaunch.service.whitelist.WhiteListService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WhiteListServiceTest {

        @InjectMocks
        private WhiteListService whiteListService;

        @Mock
        private WhiteListRepository repository;

        @Test
        public void whenValidId_thenProductShouldDeleteSucess() {
            HttpStatus a2 = ResponseEntity.ok().build().getStatusCode();
            ResponseEntity a = whiteListService.deleteWhiteList();

            Assertions.assertThat(a.getStatusCode())
                    .isEqualTo(a2);
        }

        @Test
        public void whenValidWhiteList_thenWhiteListShouldAddSucess(){

            WhiteList found = new WhiteList(11L, "White list", "88");
            when(repository.save(any())).thenReturn(found);

            found = whiteListService.addWhiteList(found);

            Assertions.assertThat(found.getId()).isNotNull();
            Assertions.assertThat(found.getName()).isEqualTo("White list");

        }

        @Test(expected = AssertionError.class)
        public void whenInvalidWhiteList_thenWhiteListShouldAddFail(){

            WhiteList found = new WhiteList();
            when(repository.save(any())).thenReturn(found);

            found = whiteListService.addWhiteList(found);

            Assertions.assertThat(found.getId()).isNotNull();
            Assertions.assertThat(found.getName()).isEqualTo("White list");

        }

        @Test
        public void whenSearch_thenGetReturnListSucess(){
            List<WhiteList> found = whiteListService.getAllWhiteLists();

            Assertions.assertThat(found.size()).isNotNull();
        }

}

