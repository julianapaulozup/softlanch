package softLaunch.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import softLaunch.domain.Response;
import softLaunch.exceptionHandler.ClientNotFoundInWhitelistException;
import softLaunch.repository.WhiteListRepository;
import softLaunch.domain.WhiteList;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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

            WhiteList test = new WhiteList(11L, "White list", "88");
            when(repository.save(any())).thenReturn(test);
            Response found = whiteListService.addWhiteList(test);

            Assertions.assertThat(found.getCpf()).isEqualTo("88");
            Assertions.assertThat(found.getName()).isEqualTo("White list");

        }

        @Test(expected = AssertionError.class)
        public void whenInvalidWhiteList_thenWhiteListShouldAddFail(){

            WhiteList test = new WhiteList();
            when(repository.save(any())).thenReturn(test);
            Response found = whiteListService.addWhiteList(test);

            Assertions.assertThat(found.getCpf()).isEqualTo("88");
            Assertions.assertThat(found.getName()).isEqualTo("White list");

        }

        @Test
        public void whenSearch_thenGetReturnListSucess(){
            PageRequest pageRequest = PageRequest.of(0,10);
            WhiteList whiteList= new WhiteList(11L,"Nome","111111");

            List<WhiteList> allClients = Arrays.asList(whiteList);
            final Page<WhiteList> page = new PageImpl<>(allClients);
            given(whiteListService.getAllWhiteLists(any(PageRequest.class))).willReturn(page);

            Page<WhiteList> found = whiteListService.getAllWhiteLists(pageRequest);
            Assertions.assertThat(found.getTotalElements()).isEqualTo(1);

        }

        @Test(expected = ClientNotFoundInWhitelistException.class)
        public void whenDoesntExistinWhitelist_theThrowException(){
            WhiteList whiteList= new WhiteList("Nome","111111");
            given(whiteListService.exists(any())).willThrow(ClientNotFoundInWhitelistException.class);

            whiteListService.exists(whiteList.getCpf());

        }

}

