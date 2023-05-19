/**
 * 문자열 유틸리티 클래스이다. 
 */
class StringUtils { 

    /**
     * 시간문자열을 시간형식으로 변환한다.
     * @param timeStr "HHMM" 문자열
     * @returns     "HH:MM" 문자열
     */
    static formatTime(timeStr : string): string {
        if(timeStr == null || timeStr == '') return 'N/A'
        if(timeStr.length < 4) return timeStr;
        return timeStr.substring(0,2) + ':' + timeStr.substring(2,4);
    }

}// StringUtils

export { StringUtils };