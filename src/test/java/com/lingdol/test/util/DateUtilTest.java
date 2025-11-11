package com.lingdol.test.util;

import com.lingdol.common.util.DateUtil;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilTest {

    @Test
    public void sameDayTest() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2025-11-10 23:59:59");
        System.out.println(DateUtil.isSameDay(date, new Date()));
    }

}
