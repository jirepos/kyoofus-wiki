<script>

    import OrgEmpMemoQuery from '../../api/org/query/OrgEmpMemoQuery';
    export let empId; 
    let empMemos; 
    let query = new OrgEmpMemoQuery();
    let cssDisplay = false;

    // fn 
    const insertMemo = () => {
        let memoObj = document.getElementById('memo'); 
        let memo = document.getElementById('memo').value;
        query.insertMemo(empId, memo)
        .then( res => {
            console.log(res.data.insertMemo)
            memoObj.value ="";
            cssDisplay = false;
            getEmpMemos();
            
        })
        .catch((error) => {
            // to do : error
            console.error(error);
        });
    }// insertMemo 
    const getEmpMemos = () => { 
        query.getMemosOfEmp(empId)
        .then( res => {
            empMemos = res.data.memosOfEmp;
        })
        .catch((error) => {
            // to do : error
            console.error(error);
        });
    }//:


    const btnAddClicked = () => {
        cssDisplay = true;
    }//:
    
    const btnCancelClicked = () => {
        cssDisplay = false;
    }//:

    const delBtnClicked = (memId) => {
        query.deleteMemo(memId)
        .then( res => {
            getEmpMemos();
        })
        .catch((error) => {
            // to do : error
            console.error(error);
        });
    }//:

    // init
    $: if(empId) {
        getEmpMemos();
    }// if 

</script>
<!-- 직원 메모 컴포넌트 -->
<div class="emp-memo-main">
        {#if empMemos}
            {#each empMemos as item}
                <div class="emp-memo-item">
                    <div class="emp-memo-item-date">{item.regDt}</div>
                    <div class="emp-memo-item-memo">{item.memo}</div>
                    <div class="emp-memo-btn">
                            <button on:click={delBtnClicked(item.memoId)} data-memo-id={item.memoId} class="btn btn-circle">
                                <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" /></svg>
                            </button>
                    </div>
                </div>
            {/each}
        {/if}

        <div>
            <button id="btnAdd" on:click={btnAddClicked} class="btn btn-active btn-primary">추가</button>
            <button id="btnCancel" on:click={btnCancelClicked} class="btn btn-active btn-secondary">취소</button>
            <button id="btnSave" class="btn btn-active" on:click={insertMemo}>저장</button>
        </div>  
        {#if cssDisplay}
            <div id="memo-add-div"><textarea id="memo"  placeholder="입력하세요"></textarea></div>
        {/if}
</div>
<style>
    .emp-memo-main {
        margin: 5px; 
        border: 1px solid #ccc;
    }
    .emp-memo-item { 
        display: flex; 
        justify-content: space-between;
        align-items: center;
    }

    .emp-memo-item-date {
        flex: 0;
        margin: 5px; 
    }
    .emp-memo-item-memo {
        flex: 1;
        margin: 5px; 
    }
    .emp-memo-btn {
        flex: 0;
        margin: 5px;
    }

    #memo-add-div textarea {
        width: 99%;
        margin: 5px;
    }
</style>
