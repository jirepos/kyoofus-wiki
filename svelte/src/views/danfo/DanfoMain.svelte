<script>
import { onMount } from "svelte";
import WorkHour from "./WorkHour.svelte";


let s = new dfd.Series([1, 3, 5, undefined, 6, 8])

                               
// function
const clickme = () => {
  //df.plot("table").line()
  s.plot('table').table();
}


// life cycle
onMount(async () => {
  //df.plot('table').table();
  //df.plot("table").line()
});



</script>

<h1>DanfoMain</h1>
<div id="table"></div>
<button on:click={clickme}>Click</button>
<hr>
<WorkHour/>