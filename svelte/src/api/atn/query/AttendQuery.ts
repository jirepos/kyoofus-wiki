import { ajax } from '../../common/ajax'
import { GraphQLQueryBase } from '../../common/GraphQLQueryBase';


/**
 * 
 */
class AtendQuery extends GraphQLQueryBase {


    /** default constructor  */
    constructor() { super();}

    /**
     * 직원목록을 조회한다. 
     * @returns 직원목록을 담은 객체 
     */
    public async getAtnCards(p_emp_id: string, p_date: string) : Promise<any> {
        const query = `query ATN_CARDS($empId:String, $date:String) {
            attendCardsOfWeek(empId: $empId, date: $date) {
                cmpId, 
                empId,
                attnDate,
                attnYy,
                attnTime,
                lvofTime
            }
        }`;
        const variables = { empId:   p_emp_id, date: p_date };
        return await ajax(this.endPoint, this.getOptions(query, variables));
    }//:





    public async getAbsences(empId: string, absnYy:string, absnMm: string): Promise<any> {
        const query = `query GET_ABSENCE($input: AtnAbsenceInput) {
               absences(input: $input) {
                 empId,
                 absnYy,
                 absnMm,
                 absnBgnDe,
                 absnEndDe,
                 absnWhy, 
                 allDayYn
               }        
            }`;
        const variables = { input: { 
            empId: empId, 
            absnYy: absnYy, 
            absnMm: absnMm } 
        };
        return await ajax(this.endPoint, this.getOptions(query, variables));
    }//:

    public async getAnnLeaveStat(empId: string, year:string): Promise<any> {
        const query = `query GET_ANN_LEAVE_STAT($empId: String, $year: String ) {
            annLeaveStat(empId: $empId, year: $year) {
                empId,
                acctBgnDate,
                totYhldCnt,
                useYhldCnt,
                remdYhldCnt
            }
        }`;
        const variables = { 
            empId: empId, 
            year: year   
        };
        return await ajax(this.endPoint, this.getOptions(query, variables));
    }//:



}///~

export default AtendQuery;
