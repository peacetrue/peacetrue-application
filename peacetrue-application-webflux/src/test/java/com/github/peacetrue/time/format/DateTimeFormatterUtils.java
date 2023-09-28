//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.github.peacetrue.time.format;

import java.text.MessageFormat;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class DateTimeFormatterUtils {
    public static final DateTimeFormatter FLEX_ISO_LOCAL_DATE_TIME = getFlexibleDateTimeFormatter('-');

    private DateTimeFormatterUtils() {
    }

    private static DateTimeFormatter getFlexibleDateTimeFormatter(char separator) {
        //TODO JDK 从 8 升级到 17 后，LocalDateTime.toSting() 返回的内容发生了变化
        // JDK 8 毫秒 -> JDK 17 微秒
        return (new DateTimeFormatterBuilder()).appendPattern(MessageFormat.format("yyyy[''{0}''MM[''{0}''dd[''T''HH['':''mm['':''ss[''.''SSSSSS]]]]]]", separator)).parseDefaulting(ChronoField.MONTH_OF_YEAR, (long)Month.JANUARY.getValue()).parseDefaulting(ChronoField.DAY_OF_MONTH, 1L).parseDefaulting(ChronoField.HOUR_OF_DAY, 0L).parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0L).parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0L).toFormatter();
    }
}
