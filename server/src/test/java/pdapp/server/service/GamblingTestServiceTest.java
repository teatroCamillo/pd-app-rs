package pdapp.server.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pdapp.server.model.GamblingTest;
import pdapp.server.model.User;
import pdapp.server.repository.GamblingTestRepository;
import pdapp.server.repository.UserDetailsRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pdapp.server.util.Constant.NOT_ADDICTED;

@ExtendWith(SpringExtension.class)
class GamblingTestServiceTest {

    @Mock
    private GamblingTestRepository gamblingTestRepository;

    @Mock
    private UserDetailsRepository userDetailsRepository;

    @InjectMocks
    private GamblingTestService gamblingTestService;
    private User user;
    private GamblingTest expected;

    @BeforeEach
    void init() throws ParseException {
        user = new User();
        user.setMail("test@gmail.com");
        user.setFirstName("TestName");
        user.setLastName("TestLastName");
        user.setEnabled(true);
        user.setPassword("test");
        user.setUsername("TestUsername");
        user.setId("cf5202e6-45ec-440e-ae17-b5662ec69f26");

        expected = new GamblingTest();
        expected.setId("fba37285-d814-4b11-b3d1-3a89ba2ae00c");
        String[] ans = new String[] {"No","No","No","No","No","No","No","No","No","No","No","No","No","No","No","No","No","No","No","No"};
        expected.setAnswers(ans);
        expected.setGtResult(NOT_ADDICTED);
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse("12/01/2018");
        expected.setCreatedAt(date);
        expected.setUser(user);
    }

    @Test
    void should_correctly_save_the_gambling_test() {
        when(userDetailsRepository.findById(any())).thenReturn(Optional.of(user));

        when(gamblingTestRepository.save(any(GamblingTest.class))).thenReturn(expected);

        Optional<GamblingTest> gamblingTest = gamblingTestService.saveGamblingTest("cf5202e6-45ec-440e-ae17" +
                "-b5662ec69f26", expected);

        assertThat(expected.getId()).isSameAs(gamblingTest.get().getId());
    }

    @Test
    void should_correctly_return_gambling_test_results_for_user() {

        when(gamblingTestRepository.findByUserId("cf5202e6-45ec-440e-ae17-b5662ec69f26")).thenReturn(expected);

        String result = gamblingTestService.getUserGamblingTestResult("cf5202e6-45ec-440e-ae17-b5662ec69f26");

        assertEquals(expected.getGtResult(), result);
    }
}