package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.dao.dto.SumBalancePrivateAccounts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.*;


import java.util.List;

@SpringBootTest
class PrivateAccountDaoTest {

    @Autowired
    PrivateAccountDao privateAccountDao;

//    @Test
//    void existsByIban() {
//    }

    @Test
    void getSumBalancePerPrivateAccount() {
        List<SumBalancePrivateAccounts> result = privateAccountDao.getSumBalancePerPrivateAccount(PageRequest.of(0,10));
        assertThat(result).isNotNull().isNotEmpty();
        for (SumBalancePrivateAccounts sumBalancePrivateAccounts : result) {
            System.out.println(sumBalancePrivateAccounts.getAccountHolder());
            System.out.println(sumBalancePrivateAccounts.getSumBalance());
        }
//        result.forEach(System.out::println);
    }


}