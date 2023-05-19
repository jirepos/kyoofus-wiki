<script>
  let count = 2;
  let employees = fetch('http://localhost/graphql', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        query: `{
          employee {
            empId, 
            userName ,
            psnEmail
          }
        }`
      })
    })
    .then( (response) => response.json() )
    // .then( (data) => {
    // 	console.log(data.data.employee.empName)
    // })

  let employeearr; 
  employees.then(  (data) => {
      console.log(data.data.employee);
      employeearr = data.data.employee; 
  }); 
</script>

<h1>EmployeeList: { count}</h1>
<button on:click={() => count++}>+</button>

<ul>
  {#if employeearr}
    {#each employeearr as item}
      <li>{item.userName} :  {item.psnEmail}</li>
    {/each}
  {/if}
</ul>