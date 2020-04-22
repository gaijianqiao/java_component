package gc.information.getImageInfo.util;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    private static final String timeZone = "CTT";
    private static final ZoneId zoneId = TimeZone.getTimeZone(timeZone).toZoneId();

    private static Date localDate2DateOfDayStart(LocalDate localDate) {
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(zoneId);
        return Date.from(zonedDateTime.toInstant());
    }

    private static Date localDate2DateOfDayEnd(LocalDate localDate) {
        LocalDateTime localDateTime = localDate.atTime(23, 59, 59);
        return Date.from(localDateTime.atZone(zoneId).toInstant());
    }

    // now week
    public static DateRange getDateRangeWeekFromNow(){
        DateRange dateRange = new DateRange();
        LocalDate nowDate = LocalDate.now(zoneId);
        LocalDate sunday = nowDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        LocalDate monday = nowDate.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));

        dateRange.setStartDate(localDate2DateOfDayStart(monday));
        dateRange.setEndDate(localDate2DateOfDayEnd(sunday));
        return dateRange;
    }
//多少个星期之前到现在周末的时间
    public static DateRange getNearDateRangeWeekFromNow(long weeks){
        DateRange dateRange = new DateRange();
        LocalDate nowDate = LocalDate.now(zoneId);
        LocalDate sunday = nowDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        LocalDate monday = nowDate.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        LocalDate previousMonday = monday.minusWeeks(weeks);

        dateRange.setStartDate(localDate2DateOfDayStart(previousMonday));
        dateRange.setEndDate(localDate2DateOfDayEnd(sunday));

        return dateRange;

    }
// previous week  多少个星期之前的一周时间
    public static DateRange getDateRangeWeekFromNow(long weeks){
        DateRange dateRange = new DateRange();
        LocalDate nowDate = LocalDate.now(zoneId);
        LocalDate sunday = nowDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        LocalDate monday = nowDate.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        LocalDate previousMonday = monday.minusWeeks(weeks);
        LocalDate previousSunday = sunday.minusWeeks(weeks);

        dateRange.setStartDate(localDate2DateOfDayStart(previousMonday));
        dateRange.setEndDate(localDate2DateOfDayEnd(previousSunday));

        return dateRange;
    }
    //    get near n day by now
    public static DateRange getDateRangeFromPreviousToNow(long days) {

        DateRange dateRange = new DateRange();

        LocalDate nowDate = LocalDate.now(zoneId);

        LocalDate startDate = nowDate.minusDays(days);

        dateRange.setStartDate(localDate2DateOfDayStart(startDate));

        dateRange.setEndDate(localDate2DateOfDayEnd(nowDate));
        return dateRange;
    }

    public static class DateRange {
        private Date startDate;
        private Date endDate;

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }
    }


}
