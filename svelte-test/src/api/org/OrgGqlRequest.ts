import { ajax } from '@/api/common/ajax'

/**
 * 
 */
class OrgGqlRequest {
    /** default constructor  */
    constructor() {}

    /**
     * 직원목록을 조회한다. 
     * @returns 직원목록을 담은 객체 
     */
    public async getAtnCards(p_emp_id: string) : Promise<any> {
        const query = `query ATN_CARDS($empId:String)  {
            attendCards(empId: $empId) {
                cmpId, 
                empId,
                attnDate,
                attnYy,
                attnTime 
            }
        }`;
        const variables = { empId:   p_emp_id };

        const url = 'http://localhost/graphql';

        let options = { 
            method: 'POST',
            headers:
            {
                 'Content-Type': 'application/json',
            },
            //body: JSON.stringify({ query })
            body: { query , variables }
        }// options 
        return await ajax(url, options);
    }//:
}///~

export default OrgGqlRequest;
