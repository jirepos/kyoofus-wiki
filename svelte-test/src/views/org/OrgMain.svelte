<script>
  import  EmployeeList from "@/views/org/EmployeeList.svelte";
  import  AttendCard from "@/views/org/AttendCard.svelte";

  let prop_empId = "M66917164";
  </script>
  
  <EmployeeList />
  <AttendCard {prop_empId} />