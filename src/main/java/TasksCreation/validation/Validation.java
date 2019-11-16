package TasksCreation.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Validation {

    /*
    * Валидация даты
    * */
    public static boolean isDateValid(String start, String end) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);

        try {
            format.parse(start);
            format.parse(end);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
