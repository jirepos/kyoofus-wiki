import dayjs from 'dayjs'; // ES 2015

/**
 * 날짜 유틸리티이다.
 */
class DateTimeUtils {
    /** 오늘 날짜를 구한다.  */
    static getToday(): string {
        var now = dayjs();
        return now.format('YYYY-MM-DD');
    }
    
    static calcAge(birthDate:string): string {
        let now = dayjs();
        let birth = DateTimeUtils.toDate(birthDate, 'YYYY-MM-DD');
        let age = now.diff(birth, 'year');
        
        console.log(age); 
        debugger; 
        return (age +1).toString(); 
    }

    /**
     * 주어진 날짜보다 7일 이전을 구한다.
     * @param date  "YYYY-MM-DD" 문자열
     * @returns     "YYYY-MM-DD" 문자열
     */
    static getPrevWeekDate(date: string): string {
        let ldate = dayjs(date);
        return dayjs(ldate).subtract(7, 'day').format('YYYY-MM-DD');
    }

    /**
     * 주어진 날짜보다 7일 이후를 구한다.
     * @param date  "YYYY-MM-DD" 문자열
     * @returns     "YYYY-MM-DD" 문자열
     */
    static getNextWeekDate(date: string): string {
        let ldate = dayjs(date);
        return dayjs(ldate).add(7, 'day').format('YYYY-MM-DD');
    }


    static getStartAndEndDate(date:string): object{
        let ldate = dayjs(date);
        let monday = ldate; 
        let sunday = ldate; 
        while (monday.get("day") != 1) {
            monday = dayjs(monday).subtract(1, 'day')
        }
        while (sunday.get("day") != 0) {
            sunday = dayjs(sunday).add(1, 'day')
        }
        return { monday: monday.format('YYYY-MM-DD'), sunday: sunday.format('YYYY-MM-DD') };
    }//:

    static format(date:string, format:string):string{ 
        return dayjs(date).format(format);
    }
    static toDate(date:string, format:string):any{ 
        return dayjs(date, format); 
    }
} ///~

export { DateTimeUtils };
