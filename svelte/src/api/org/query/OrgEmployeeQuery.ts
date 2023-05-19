import { ajax } from '../../common/ajax'
import { GraphQLQueryBase } from '../../common/GraphQLQueryBase';


/**
 * 직원 정보 요청 Graphql 클라이언트이다. 
 */
class OrgEmployeeQuery extends GraphQLQueryBase {


    /** default constructor  */
    constructor() { super();}


    public async getEmployees(p_dept_id:string) : Promise<any> {
        const query = `query EMPLOYEE_LIST($deptId:String) {
            employeesOfDept(deptId: $deptId) {
                empId, 
                userName ,
                cmpEmail,
                profileImg
            }
        }`;
        const variables = { deptId:   p_dept_id };
        return await ajax(this.endPoint, this.getOptions(query, variables));
    }//:




    public async getSubDepts(upDeptId: string): Promise<any> {
        const query = `query SUB_DEPT_LIST($upDeptId:String) {
            subDepartments(upDeptId: $upDeptId) {
                deptId,
                deptName, 
                deptLoc,
                deptSortOrdr 
            }
        }`;
        const variables = { upDeptId:   upDeptId };
        return await ajax(this.endPoint, this.getOptions(query, variables));
    }//:


    public async getEmployeeInfo(empId: string): Promise<any> {
        const query = `query EMPLOYEE_INFO($empId:String) {
            employeeInfo(empId: $empId) {
                psnId
                cellPhone
                profileImg
                birthDate
                cmpId
                empId
                userName
                psnEmail
                cmpEmail
                userId
                ecnyDate 
                secLvl
                updDt
                deptId
                deptName
                posId
                posName
                ofcId
                ofcName                
            }
        }`;
        const variables = { empId:   empId };
        return await ajax(this.endPoint, this.getOptions(query, variables));
    }//:


}///~

export default OrgEmployeeQuery;
