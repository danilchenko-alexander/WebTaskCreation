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
            if(!start.isEmpty()) {
                format.parse(start);
            }
            if(!end.isEmpty()) {
                format.parse(end);
            }
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
