<script context="module">
    //태그는 모든 컴포넌트가 인스턴스되었을 때 실행되는 것이 아닌 처음 script를 읽혔을 때 실행 됩니다. 보통의 script태그와 마찬가지로 변수를 선언 가능합니다.
    // 반응성을 구현 할 수 없습니다. 만약 다양한 컴포넌트에서 반응성을 필요로 한다면 store를 생성하는 것을 고려하세요 .
    // context="module"로 지정된 <script> 태그 내에 있는 모든 것들이 Export 됨
    // retain module scoped expansion state for each tree node
    const _expansionState = {
        /* treeNodeId: expanded <boolean> */
    };
    // 모든 트리 컴포넌에서 선택된 트리 아이템에 배경색과 같은 css를 적용하기 위해
    // 호출할 함수에 대한 콜백 보관
    const callbacks = new Set();
    /**
     * 모든 트리 컴포넌트에서 선택된 트리 아이템에 배경색과 같은 css를 적용하기 위해 콜백 함수 호출
     * @param empId 사용자 아이디
     */
    const notifyAll = (empId) => {
        callbacks.forEach((cb) => cb(empId));
    };
</script>


<script>
    import { createEventDispatcher } from 'svelte';
    const dispatch = createEventDispatcher();

    export let treeItems;
    const { label, deptOrEmpId, cmpEmail, trgtType, profileImg, children } = treeItems;

    // 트리 아이템 펼치기 여부 판단 변수
    let expanded = _expansionState[label] || false;
    // 선택된 사용자 CSS 클래스
    $: selectedEmp = '';
    // CSS에서 트리 아이템 아래 화살표 표시 여부
    $: arrowDown = expanded;

    /**
     * 사용자가 선택되었을 때 호출되는 콜백함수로 배경색을 변경하기 위해서 호출된다.
     * @param selectedEmployee
     */
    const changeEmployee = (selectedEmployee) => {
        selectedEmp = selectedEmployee; // CSS 적용하기 위한 변수
    };


    /**
     * 트리 아이템에서 사용자를 선택하면 호출된다. 
     */
    const changeUser = () => {
        notifyAll(deptOrEmpId); // CSS 처리를 위해 호출 
        // 사용자 선택 이벤트를 발생시킨다 
        dispatch('itemSelected', {
            deptOrEmpId: deptOrEmpId,
            trgtType: trgtType,
            cmpEmail: cmpEmail
        });
    }; // changeUser

    /**
     * 상위 컴포넌트에서 사용자 정보와 하위 부서 정로를 가져온 후 
     * 트리 아이템을 펼치거나 접을 때 호출되는 콜백함수. 이 함수는 상위 콤포넌트에 전달된다. 
     */
    const toggleExpansionCallback = () => {
        console.log('toggleSub---');
        expanded = _expansionState[label] = !expanded;
        console.log(expanded);
    };

    /**
     * 트리 아이템 펼치기 여부 판단. 부서가 선택되었을 때. 
     */
    const toggleExpansionWhenDeptSelected = () => {
        // 사용자가 아니고 자식이 있다면
        if (trgtType != 'M' && children.length > 0) {
            expanded = _expansionState[label] = !expanded;
            return;
        }
        // 트리아이템 선택 이벤트 발생
        dispatch('itemSelected', {
            deptOrEmpId: deptOrEmpId,
            trgtType: trgtType,
            func: toggleExpansionCallback,
        });
    };

    // init
    // 사용자를 표시하는 트리 아이템이 선택되었을 때 실행되는 콜백함수를 등록한다.
    callbacks.add(changeEmployee);
</script>

<ul>
    <!-- transition:slide -->
    <li>
        {#if children}
            <!-- 트리 아이템 펼치기 토글 -->
            <span on:click={toggleExpansionWhenDeptSelected}>
                <span class="arrow" class:arrowDown>&#x25b6</span>
                {label}
            </span>
            {#if expanded}
                <!-- 트리 아이템이 펼쳐지면, 트리 컴포넌트 추가-->
                {#each children as child}
                    <!-- 하위 컴포넌트를 처리하지 않고  상위로 던지려면 handler function을 설정하지 않는다.-->
                    <svelte:self on:itemSelected treeItems={child} />
                {/each}
            {/if}
        {:else}
            <!-- 자식이 없으면, 사용자 -->
            <div class:emp_selected={selectedEmp == deptOrEmpId} class="middle" on:click={changeUser}>
                {#if profileImg}
                    <div class="card mb-3" style="max-width: 540px;">
                        <div class="row g-0">
                            <div class="col-md-4">
                                <img width="50px" height="60px" src={profileImg} class="img-fluid rounded-start" alt="profileimage" />
                            </div>
                            <div class="col-md-8">
                                <div class="card-body">
                                    <h5 class="card-title"><span class="no-arrow" />{label}</h5>
                                    <p class="card-text">부서</p>
                                    <!-- <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p> -->
                                </div>
                            </div>
                        </div>
                    </div>
                {:else}
                    <div class="card mb-3" style="max-width: 540px;">
                        <div class="row g-0">
                            <div class="col-md-4">
                                <!-- <img width="50px" height="60px" src={profileImg} class="img-fluid rounded-start"  alt="profileimage" /> -->
                            </div>
                            <div class="col-md-8">
                                <div class="card-body">
                                    <h5 class="card-title"><span class="no-arrow" />{label}</h5>
                                    <p class="card-text">부서</p>
                                    <!-- <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p> -->
                                </div>
                            </div>
                        </div>
                    </div>
                {/if}
            </div>
        {/if}
    </li>
</ul>

<style>
    ul {
        margin: 0;
        list-style: none;
        padding-left: 1.2rem;
        user-select: none;
    }
    .no-arrow {
        padding-left: 1rem;
    }
    .arrow {
        cursor: pointer;
        display: inline-block;
        /* transition: transform 200ms; */
    }
    .arrowDown {
        transform: rotate(90deg);
    }
    .middle {
        vertical-align: middle;
    }
</style>
