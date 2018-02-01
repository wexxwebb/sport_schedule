package common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class SDF {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String format(Date date) {
        return simpleDateFormat.format(date);
    }

    public static Result<Date> getDate(String string) {
        try {
            Date date = simpleDateFormat.parse(string);
            return new Result<>(date, true, "Success");
        } catch (ParseException e) {
            return new Result<>(null, false, e.getMessage());
        }
    }

}
