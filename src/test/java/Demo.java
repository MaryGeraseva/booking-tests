import common.TestUtilities;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Demo {

    @Test
    public void demo() {
        LocalDate departureDate = TestUtilities.getInWeeksDate(1);
        LocalDate arrivalDate = TestUtilities.getInWeeksDate(2);

        System.out.println(departureDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + departureDate.getDayOfMonth());
    }


}
