package softLaunch.repository;

import org.assertj.core.api.Assertions;
import org.junit.ComparisonFailure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import softLaunch.service.whitelist.WhiteList;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WhiteListRepositoryTest {

        @Autowired
        WhiteListRepository repository;

        @Test
        public void createShouldPersistDataSucess(){

            WhiteList whiteList = new WhiteList(11L,"WhiteList","111111");
            this.repository.save(whiteList);
            Assertions.assertThat(whiteList.getId()).isNotNull();
            Assertions.assertThat(whiteList.getName()).isEqualTo("WhiteList");
            Assertions.assertThat(whiteList.getCpf()).isEqualTo("111111");
        }

        @Test(expected = ComparisonFailure.class)
        public void createShouldPersistDataFail(){

            WhiteList whiteList = new WhiteList(11L,"WhiteList","111111");
            this.repository.save(whiteList);
            Assertions.assertThat(whiteList.getId()).isNotNull();
            Assertions.assertThat(whiteList.getName()).isEqualTo("WhiteListErro");
            Assertions.assertThat(whiteList.getCpf()).isEqualTo("111111");
        }

        @Test
        public void findWhiteListAfterSave() {

            WhiteList whiteList = new WhiteList("WhiteList", "11111");
            repository.save(whiteList);
            List<WhiteList> whiteLists = repository.findAll();
            assertEquals(7, whiteLists.size());
            int size = whiteLists.size() - 1 ;
            Assertions.assertThat(whiteLists.get(size).getId()).isNotNull();
            Assertions.assertThat(whiteLists.get(size).getCpf()).isEqualTo("11111");
            Assertions.assertThat(whiteLists.get(size).getName()).isEqualTo("WhiteList");

        }

        @Test
        public void deleteWhiteListAfterSave() {

            WhiteList whiteList = new WhiteList("WhiteList", "111111");
            repository.save(whiteList);
            List <WhiteList> foundWhiteLists = repository.findAll();
            repository.delete(foundWhiteLists.get(6));
            List <WhiteList> whiteLists = repository.findAll();
            assertEquals(6, whiteLists.size());

        }

        @Test
        public void updateWhiteListAfterSave() {


            WhiteList whiteList = new WhiteList("WhiteList", "111111");
            repository.save(whiteList);
            whiteList.setName("WhiteList Atualizada");
            repository.save(whiteList);
            List <WhiteList> whiteLists = repository.findAll();
            int size = whiteLists.size() - 1;
            assertEquals(7, whiteLists.size());
            assertEquals("WhiteList Atualizada", whiteLists.get(size).getName());
        }

        @Test
        public void returnEmptyWheNotFound(){
            Optional<WhiteList> found;
            found = repository.findById(111L);
            Assertions.assertThat(!found.isPresent());
        }

}
