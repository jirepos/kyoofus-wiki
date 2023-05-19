<script>
    import OrgEmployeeQuery from '../../api/org/query/OrgEmployeeQuery';
    import OrgEmpMemo from './OrgEmpMemo.svelte'; 
    import { DateTimeUtils } from '../../api/common/DateTimeUtils';

    export let empId;
    let employee;
    const query = new OrgEmployeeQuery();

    $: if (empId) {
        console.log('EmployeeInfo:' + empId);
        getEmployeeInfo();
    }

    const getEmployeeInfo = () => {
        query
            .getEmployeeInfo(empId)
            .then((res) => {
                console.log(res.data.employeeInfo);
                employee = res.data.employeeInfo;
                debugger;
                console.log(employee); 
                if(employee.birthDate) {
                    let age  = DateTimeUtils.calcAge(employee.birthDate);
                    console.log("age:" + age);
                    employee.age = age; 
                }
                
            })
            .catch((err) => {
                console.log(err);
            });
    };
</script>

<div class="emp-info-main">
    {#if employee}
        <div style="text-align:center;">
                    {#if employee.profileImg}
                        <img src="https://my.naonsoft.com/ekp/service/file/fileView?fileUrl={employee.profileImg}" width="100" height="120" alt=""> 
                    {/if}
            <div class="emp-info-center">{employee.deptName}</div>
            <div class="emp-info-center">{employee.userName} {employee.posName}</div>
            <div class="emp-info-center">{employee.cmpEmail}</div>
            <div class="emp-info-center">{employee.cellPhone}</div>
        </div>
        <div>
            <div class="tabs">
                <span class="tab tab-lifted tab-active">기본정보</span>
                <span class="tab tab-lifted">학력사항</span>
            </div>
            <div>
                <div class="emp-info-table">
                    <table>
                        <tr>
                            <th>입사일</th>
                            <td>{employee.userName}</td>
                        </tr>
                        <tr>
                            <th>생일</th>
                            <td>
                                {#if employee.birthDate}
                                    {DateTimeUtils.format(employee.birthDate, 'YYYY-MM-DD')}({employee.age}세)
                                {/if}
                            </td>
                        </tr>
                        <tr>
                            <th>개인 이메일</th>
                            <td>{employee.userName}</td>
                        </tr>
                        <tr>
                            <th>입사일</th>
                            <td>
                                {#if employee.ecnyDate}
                                    {DateTimeUtils.format(employee.ecnyDate, 'YYYY-MM-DD')}
                                {/if}
                            </td>
                        </tr>

                    </table>
                </div>
            </div>
        </div>
    {/if}

</div>
<div>
    <OrgEmpMemo {empId}/>
</div>

<style>
    .emp-info-center {
        text-align: center;
    }
    .emp-info-main {
        margin: 10px;
        display: flex;
        flex-direction: row;
    }
</style>
