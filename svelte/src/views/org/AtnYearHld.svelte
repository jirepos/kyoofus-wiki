<script>
    import AtendQuery from '../../api/atn/query/AttendQuery';

    // props
    export let empId; // 사원 아이디
    export let year; // 근태 기준 연도
    let query = new AtendQuery();

    let alStat;

    // watch
    $: if (empId && year) {
        getAnnLeaveStat();
    } // $:

    // functions
    const getAnnLeaveStat = () => {
        query
            .getAnnLeaveStat(empId, year)
            .then((res) => {
                console.log((alStat = res.data.annLeaveStat));
                alStat = res.data.annLeaveStat[0];
            })
            .catch((error) => {
                // to do : error
                console.error(error);
            });
    }; //:
</script>
<style>

    .ann-main-div { 
        margin: 5px;
    }
    .ann-table { 
        margin-top: 10px; 
        border: 1px solid #ddd;
        width: 100%; 
    }
    .ann-table tr, .ann-table td {
        border: 1px solid #ddd;
        text-align: center; 
    }

</style>


<!-- 연차현황 -->
<div class="ann-main-div">
    {#if alStat}
        <div>
            <span class="badge bg-primary">연차현황</span>
        </div>
        <div>
            <table class="ann-table">
                <tr>
                    <th>항목</th>
                    <th>횟수</th>
                </tr>
                <tr>
                    <td>총연차</td>
                    <td>{alStat.totYhldCnt}</td>
                </tr>
                <tr>
                    <td>사용연차</td>
                    <td>{alStat.useYhldCnt}</td>
                </tr>
                <tr>
                    <td>잔여연차</td>
                    <td>{alStat.remdYhldCnt}</td>
                </tr>
            </table>
        </div>
    {/if}
</div>
