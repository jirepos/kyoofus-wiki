<script>

  // import 
  import OrgGqlRequest from '@/api/org/OrgGqlRequest'
 

  // props
  export let prop_empId; 
  let attendcardarr; 


  // functions 

  // const getAtnCard = (p_emp_id) => { 
  //       const query =`query ATN_CARDS($empId:String) {    
  //           attendCards(empId: $empId) {
  //               cmpId, 
  //               empId,
  //               attnDate,
  //               attnYy,
  //               attnTime 
  //          }
  //      }`;
  //      const variables = { empId:   p_emp_id };

  //      let attendcards = fetch('http://localhost/graphql', {
  //                method: 'POST',
  //                 headers: {
  //                   'Content-Type': 'application/json'
  //                 }  ,
  //                 body: JSON.stringify({  query,  variables })
  //               })
  //     .then( (response) => response.json() )
      
  //     attendcards.then(  (data) => {
  //       console.log(data.data.attendCards); // for debug
  //       attendcardarr = data.data.attendCards; 
  //     }); 
  //   };

    // if(prop_empId ) {
    //   getAtnCard(prop_empId);
    // }

    let orgRequest = new OrgGqlRequest();

    if(prop_empId) {
      orgRequest.getAtnCards(prop_empId)
      .then( res => {
        console.log(attendarr);
        attendcardarr = data.data.attendCards; 
      })
      .catch(error => {
        // to do : error 
      })

    }

</script>


<ul>
  {#if attendcardarr}
    {#each attendcardarr as item}
      <li>{item.empId} :  {item.attnDate} : {  item.attnTime} </li>
    {/each}
  {/if}
</ul>
