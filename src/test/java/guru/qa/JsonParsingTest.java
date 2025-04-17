package guru.qa;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.Model.Application;
import guru.qa.Model.Loans;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonParsingTest {
    @Test
    void testJsonParsing() throws Exception {

        try (InputStream is = getClass().getClassLoader().getResourceAsStream("Application.json")) {

            ObjectMapper objectMapper = new ObjectMapper();

            Application application = objectMapper.readValue(is, Application.class);

            assertEquals("Прямое", application.getkNIncomeConfirmationType());
            assertEquals("CashLoan", application.getSearchType());
            assertEquals("N", application.getVipClientFlg());
            assertEquals("Y", application.getAvailableLimitDecrease());
            assertTrue(application.isEnableKnAdditionalService());
            assertEquals(0, application.getExistProfileCardFlag());

           List<Loans> currentLoans = application.getCurrentLoans();
           assertEquals(2, currentLoans.size());

           Loans firstLoan = currentLoans.getFirst();

           assertEquals("01bebd95-1db9-447c-8617-de815b415938", firstLoan.getId());
           assertEquals("3KK-651019426831", firstLoan.getDocNumber());
           assertEquals("Кредитная карта", firstLoan.getType());
           assertEquals("INT", firstLoan.getCreditHistoryType());
           assertEquals("RUR", firstLoan.getCurrencyCode());

            Loans secondLoan = currentLoans.getLast();

            assertEquals("ebb3c713-bc6e-4c62-a807-633c52b986be", secondLoan.getId());
            assertEquals("2KK-651019414700", secondLoan.getDocNumber());
            assertEquals("Кредитная карта", secondLoan.getType());
            assertEquals("INT", secondLoan.getCreditHistoryType());
            assertEquals("RUR", secondLoan.getCurrencyCode());

        }
    }
}
