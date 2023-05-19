<script>
    import EmployeeList from './EmployeeList.svelte'
    import AttendCards from './AttendCards.svelte'
    import { DateTimeUtils } from '../../api/common/DateTimeUtils'
    import AtnAbsence from './AtnAbsence.svelte'
    import AtnYearHld from './AtnYearHld.svelte'
    import EmployeeInfo from './EmployeeInfo.svelte'
    import EmailList from '../email/EmailList.svelte'
    


    let prop_empId;
    let prop_name;
    let today = DateTimeUtils.getToday();
    let prop_date = today; 
    let format = "YYYY-MM-DD";
    let cmpEmail;
    let absnYy = DateTimeUtils.toDate(prop_date, format).get("year");
    let absnMm = DateTimeUtils.toDate(prop_date, format).format("MM");


    // capture events 
    const userChanged = (event) => {
        console.log("> event.detail:", event.detail); 
        prop_empId = event.detail.empId;
        prop_name = event.detail.empName; 
        cmpEmail = event.detail.cmpEmail;
    }
    const dateChanged = (event) => { 
        prop_date = event.detail.selDate;
        absnYy = "" + DateTimeUtils.toDate(prop_date, format).get("year");
        absnMm = DateTimeUtils.toDate(prop_date, format).format("MM");
    }
    $: if(prop_date) {
        console.log("watch prop_date");
    }
    $: if(prop_empId) {
        console.log("prop_empId:", prop_empId);
    }
</script>

<style>

    .org-main {
        display: flex;
        flex-direction: row;
        height: 100%;
    }
    .org-left {
        flex-basis: 300px; 
        min-width: 300px; 
        background-color: #f0f0f0;
        height:100%;
    }
    .org-middle { 
        flex-grow: 1; 
    }
    .org-right { 
        flex-grow: 0;
        min-width: 400px; 
        max-width: 400px;
    }
    .email-list-container { 
        display: flex; 
    }

</style>

<!-- content -->
<div class="org-main">
    <div class="org-left"><EmployeeList on:userChanged={userChanged} /></div>
    <div class="org-middle"> 
        <div>
            <EmployeeInfo empId={prop_empId} />
        </div>
        <div>
            <AttendCards {prop_empId} {prop_name}   on:changedDate={dateChanged} />
        </div>
        <div class="email-list-container">
            <div>
                <EmailList targetEmail={cmpEmail} inOutType="in" />
            </div>
            <div>
                <EmailList targetEmail={cmpEmail} inOutType="out" />
            </div>
        </div>
    </div>
    <div class="org-right">
        <div>
            <AtnYearHld empId={prop_empId} year={absnYy}   />
        </div>
        <div>
            <AtnAbsence empId={prop_empId} absnYy={absnYy} />
        </div>
    </div>
</div>


