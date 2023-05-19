<script>
    import AtendQuery from '../../api/atn/query/AttendQuery';
    import { createEventDispatcher } from 'svelte';
    

    // props
    export let empId; // 사원 아이디
    export let absnYy; // 부재년
    let absnMm; // 부재월

    // libraries
    let query = new AtendQuery();

    // variables
    $: absences = [];

    // init
    $: if (empId) {
        getAbsences();
    }
    $: if (absnYy) {
        getAbsences();
    }
    $: if (absnMm) {
        getAbsences();
    }

    const getAbsences = () => {
        query
            .getAbsences(empId, absnYy, absnMm)
            .then((res) => {
                absences = res.data.absences;
            })
            .catch((err) => {
                console.log(err);
            });
    }; // getAbsences
</script>

<div class="absence-main">
    <div>
        <span class="badge bg-primary">부재</span>
    </div>
    <div>
        <table class="absence-main-table">
            <tr>
                <th>날짜</th>
                <th>부재사유</th>
                <th>종일</th>
            </tr>
            {#if absences}
                {#each absences as item}
                    <tr>
                        <td>{item.absnBgnDe}~{item.absnEndDe}</td>
                        <td>{item.absnWhy}</td>
                        <td>{item.allDayYn}</td>
                    </tr>
                {/each}
            {/if}
        </table>
    </div>
</div>

<style>
    .absence-main {
        margin: 5px;
    }
    .absence-main-table { 
        margin-top: 10px; 
        border: 1px solid #ddd;
        width: 100%; 
    }
    .absence-main-table tr, th, td { 
        border: 1px solid #ddd;
    }

</style>
