import { ajax } from '../../common/ajax'
import { GraphQLQueryBase } from '../../common/GraphQLQueryBase';


/** 직원 메모 graphql 요청 클라이언트이다.  */
class OrgEmpMemoQuery extends GraphQLQueryBase {
    
    /** default constructor  */
    constructor() { super();}
   
    public async insertMemo(empId: string, memo: string): Promise<any> {
        const query = `mutation INSERT_MEMO($empId: String, $memo: String) {
            insertMemo(empId:$empId, memo:$memo) {
              empId
              memoId
              memo
              regDt
              updDt
            }
          }`;
          
        const variables = { empId:   empId, memo: memo  };
        return await ajax(this.endPoint, this.getOptions(query, variables));
    }//:


    public async getMemosOfEmp(empId: string): Promise<any> {
      const query = `query GET_MEMOS_OF_EMP($empId: String) {
        memosOfEmp(empId: $empId) {
          empId
          memoId
          memo
          regDt
          updDt
        }
      }`;
      const variables = { empId: empId };
      return await ajax(this.endPoint, this.getOptions(query, variables));
    }//:
    public async deleteMemo(memoId: string): Promise<any> {
      const query = `mutation DELETE_MEMO($memoId: String) {
        deleteMemo(memoId: $memoId)
      }`;
      const variables = { memoId: memoId };
      return await ajax(this.endPoint, this.getOptions(query, variables));
    }

}///~


export default OrgEmpMemoQuery;
