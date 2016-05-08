package co.edu.uniandes.misvacaciones.rest.adapters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Date> {

    /**
     * Thread safe {@link DateFormat}.
     */
    private static final ThreadLocal<DateFormat> DATE_FORMAT_TL = new ThreadLocal<DateFormat>() {

        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    @Override
    public Date unmarshal(String v) throws Exception {
        return DATE_FORMAT_TL.get().parse(v);
    }

    @Override
    public String marshal(Date v) throws Exception {
        if (v == null) {
            return null;
        }
        return DATE_FORMAT_TL.get().format(v);
    }
}
