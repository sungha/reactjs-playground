package kr.sungha.boot.jpa.repository;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.mysema.commons.lang.Pair;
import com.querydsl.core.annotations.QueryDelegate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import org.apache.commons.lang3.time.DateUtils;

public class QueryExtensions {

//  @QueryDelegate(DateTime.class)
//  public static BooleanExpression inPeriod(DatePath<DateTime> date, Pair<DateTime, DateTime> period) {
//    return date.goe(period.getFirst()).and(date.loe(period.getSecond()));
//  }

  @QueryDelegate(Timestamp.class)
  public static BooleanExpression inDatePeriod(DateTimePath<Timestamp> timestamp, Pair<Date, Date> period) {
    Timestamp first = new Timestamp(DateUtils.truncate(period.getFirst(), Calendar.DAY_OF_MONTH).getTime());
    Calendar second = Calendar.getInstance();
    second.setTime(DateUtils.truncate(period.getSecond(), Calendar.DAY_OF_MONTH));
    second.add(1, Calendar.DAY_OF_MONTH);
    return timestamp.goe(first).and(timestamp.lt(new Timestamp(second.getTimeInMillis())));
  }

}
