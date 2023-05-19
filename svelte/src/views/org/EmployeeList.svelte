<script>
    import OrgEmployeeQuery from '../../api/org/query/OrgEmployeeQuery';
    import TreeView from '../components/TreeView.svelte';
    import { createEventDispatcher } from 'svelte';

    const dispatch = createEventDispatcher();
    const query = new OrgEmployeeQuery();

    let employees;
    let savedChildren; // 현재 선택된 트리의 children

    /** Tree에 전달할 트리 아이템  */
    let treeItems;
    /** 트리아이템을 보관하기 위한 Map  */
    let map = new Map();

    // css
    let current = '';

    // fn
    const fn = {
        init() {
            //fn.getEmployees();
            fn.getCompany();
        },
        /**
         * 회사정보와 회사에 속한 직원들을 조회한다. 
         */
        getCompany() {
            treeItems = {
                label: '나온소프트',
                deptId: 'C123456789',
                trgtType: 'C',
                children: [],
            }; // departments

            // map에 회사정보 보관 
            map.set(treeItems.deptId, treeItems); 
            // 회사의 직원들 조회 
            fn.getEmployees(treeItems.deptId, null);
        }, // getCompany

        /**
         * 부서의 하위 부서를 조회한다. 
         * @param upDeptId 부서 아이디 
         * @param func 콜백함수 
         */
        getSubDepts(upDeptId, func) {
            // console.log('getSubDepts');
            let savedDept = map.get(upDeptId);
            query.getSubDepts(upDeptId).then((res) => {
                res.data.subDepartments.forEach((item) => {
                    let treeItem = {
                        label: item.deptName,
                        trgtType: 'D',
                        deptOrEmpId: item.deptId,
                        children: [],
                    };
                    map.set(treeItem.deptOrEmpId, treeItem); // 부서 아이디로 부서 참조 저장
                    savedDept.children.push(treeItem);
                }); //forEach
                if (func) {
                    func();
                }
            }); // query.getSubDepts
        }, // getsubDepts
        /**
         * 부서의 직원들을 조회한다. 
         * @param deptId 부서 아이디 
         * @param func 콜백 함수 
         */
        getEmployees(deptId, func) {
            let savedDept = map.get(deptId);
            // 부서의 직원들이 이미 있으면 다시 조회하지 않는다. 
            if (savedDept.children > 0) return;
            query
                .getEmployees(deptId)
                .then((res) => {
                    let employees = res.data.employeesOfDept;
                    if (employees != null && employees.length > 0) {
                        current = employees[0].empId;
                    }
                    employees.forEach((item) => {
                        if (!map.has(item.empId)) {
                            let treeItem = {
                                label: item.userName,
                                trgtType: 'M',
                                deptOrEmpId: item.empId,
                                cmpEmail: item.cmpEmail,
                                profileImg: item.profileImg
                                    ? 'https://my.naonsoft.com/ekp/service/file/fileView?fileUrl=' + item.profileImg
                                    : null,
                            };
                            map.set(treeItem.deptOrEmpId, treeItem);
                            savedDept.children.push(treeItem);
                        } // has
                    });
                    fn.getSubDepts(deptId, func);
                })
                .catch((error) => {
                    // to do : error
                    console.error(error);
                });
        }, // getEmployees

        changeUser(empId) {
            current = empId;
            let curItem = map.get(empId);
            // console.log("curItem");
            // console.log(curItem);
            dispatch('userChanged', {
                empId: empId,
                empName: curItem.label,
                cmpEmail: curItem.cmpEmail
            }); // dispatch 호출
        },
    }; // fn

    // event
    const handleEvent = (event) => {
        if (event.detail.trgtType == 'D') {
            fn.getEmployees(event.detail.deptOrEmpId, event.detail.func);
        } else if (event.detail.trgtType == 'M') {
            console.log('chagneUser : ' + event.detail.deptOrEmpId);
            fn.changeUser(event.detail.deptOrEmpId);
        }
    };

    // init
    fn.init();
</script>

<div class="treeview-div" id="treeViewDiv">
    {#if treeItems}
        <TreeView on:itemSelected={handleEvent} treeItems={treeItems} />
    {/if}
</div>

<style>
    .treeview-div {
        width: 100%;
        height: 100%;
        overflow: auto;
    }

    .treeview-div::-webkit-scrollbar {
        display: none; /* Chrome, Safari, Opera*/
    }
</style>
