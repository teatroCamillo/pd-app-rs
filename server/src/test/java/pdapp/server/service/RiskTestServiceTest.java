package pdapp.server.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pdapp.server.model.RiskTest;
import pdapp.server.model.User;
import pdapp.server.repository.RiskTestRepository;
import pdapp.server.repository.UserDetailsRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pdapp.server.util.Constant.AVERAGE_RISK_TOLERANCE;

@ExtendWith(SpringExtension.class)
class RiskTestServiceTest {

    @Mock
    private RiskTestRepository riskTestRepository;

    @Mock
    private UserDetailsRepository userDetailsRepository;

    @InjectMocks
    private RiskTestService riskTestService;
    private User user;
    private RiskTest expected;

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

        expected = new RiskTest();
        expected.setId("fba37285-d814-4b11-b3d1-3a89ba2ae00c");
        String[] ans = new String[] {"d. A real gambler","a. $1,000 in cash","a. Cancel the vacation",
                "c. Invest it in stocks or stock mutual funds","a. Not at all comfortable","a. Loss",
                "c. Sell the bonds and put the total proceeds into hard assets",
                "b. $800 gain best case; $200 loss worst case","a. A sure gain of $500",
                "a. A sure loss of $500","a. A savings account or money market mutual fund",
                "b. 30% in low-risk investments 40% in medium-risk investments 30% in high-risk investments",
                "d. Six month’s salary"};

        expected.setAnswers(ans);
        expected.setRtResult(AVERAGE_RISK_TOLERANCE);
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse("12/01/2018");
        expected.setCreatedAt(date);
        expected.setUser(user);
    }

    @Test
    void should_correctly_save_the_risk_test() {
        when(userDetailsRepository.findById(any())).thenReturn(Optional.of(user));

        when(riskTestRepository.save(any(RiskTest.class))).thenReturn(expected);

        Optional<RiskTest> gamblingTest = riskTestService.saveRiskTest("cf5202e6-45ec-440e-ae17" +
                "-b5662ec69f26", expected);

        assertThat(expected.getId()).isSameAs(gamblingTest.get().getId());
    }

    @Test
    void should_correctly_return_risk_test_results_for_user() {
        when(riskTestRepository.findByUserId("cf5202e6-45ec-440e-ae17-b5662ec69f26")).thenReturn(expected);

        String result = riskTestService.getUserRiskTestResult("cf5202e6-45ec-440e-ae17-b5662ec69f26");

        assertEquals(expected.getRtResult(), result);
    }
}