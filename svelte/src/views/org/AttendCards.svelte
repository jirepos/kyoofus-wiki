<script>
    // import
    import AtendQuery from '../../api/atn/query/AttendQuery';
    import { DateTimeUtils } from '../../api/common/DateTimeUtils';
    import { StringUtils } from '../../api/common/StringUtils';
    import dayjs from 'dayjs';
    import { createEventDispatcher } from 'svelte';

    // props
    export let prop_empId;
    export let prop_name;

    // dispatch 이벤트를 발생시키는 함수 생성
    const dispatch = createEventDispatcher();

    // variables
    let attendcardarr;
    let query = new AtendQuery();
    let prop_date = DateTimeUtils.getToday();
    let calDate = prop_date;
    let df;

    let period; // { monday, sunday }

    // functions
    // 근무시간 계산
    const execWorkHours = (row) => {
        let startIdx = 6;
        let endIdx = 7;

        if (row[startIdx] == '' || row[endIdx] == '') return '';
        if (row[startIdx].length < 13 || row[endIdx].length < 13) return '';

        let startDate = dayjs(row[startIdx].trim(), 'YYYYMMDD HHmm');
        let endDate = dayjs(row[endIdx].trim(), 'YYYYMMDD HHmm');

        let hour = endDate.diff(startDate, 'hour');
        if (hour > 8) hour = hour - 1;
        let minute = endDate.diff(startDate, 'minute') % 60;
        let retVal = hour + ' h,' + minute + ' m';
        return retVal;
    }; // execWorkHours

    // 문자열을 값으로 가지는 Series를 합치기
    const concatSeries = (s1, s2) => {
        let s2_vals = s2.values.map((x) => ' ' + x);
        let s3 = s1.str.concat(s2_vals, 1);
        //s3.print();
        return s3;
    }; // concatSeries

    // event
    const doEvent = () => {
        console.log('dispatch event', calDate);
        dispatch('changedDate', {
            selDate: calDate,
        });
    };

    // functions
    const fn = {
        formatAttendCards(attendCards) {
            attendCards.forEach((item, index, arr) => {
                console.log(item.cmpId);
            });
        },

        doDataFrame(attendCards) {
            let df1 = new dfd.DataFrame(attendCards);

            //df1.print();
            df1.fillNa(0, { inplace: true });
            //df1.print();
            //df1.ctypes.print();

            df1 = df1.asType('attnDate', 'string');
            df1 = df1.asType('attnTime', 'string');
            df1 = df1.asType('lvofTime', 'string');

            let new_series = concatSeries(df1['attnDate'], df1['attnTime']);
            console.log(new_series);
            df1 = df1.addColumn('start', new_series); // 출근시간 문자열

            new_series = concatSeries(df1['attnDate'], df1['lvofTime']);
            df1 = df1.addColumn('end', new_series); // 퇴근시간 문자열
            //df1.ctypes.print();
            df1.addColumn('work_hours', df1.apply(execWorkHours), { inplace: true });
            return df1;
        },
        // 날짜를 오늘날짜로 설정
        setToday() {
            let today = DateTimeUtils.getToday();
            prop_date = today;
            calDate = today;
            doEvent();
        },
        // input=date 날짜 변경시
        setDate() {
            //console.log("this:" + this.value);
            //getStartAndEndDate(this.value);
            prop_date = this.value;
            calDate = prop_date;
            doEvent();
        },
    };

    const getAtncards = (empId, date) => {
        if (prop_empId) {
            query
                .getAtnCards(prop_empId, prop_date)
                .then((res) => {
                    attendcardarr = res.data.attendCardsOfWeek;
                    fn.formatAttendCards(attendcardarr);
                    df = fn.doDataFrame(attendcardarr);

                    let df2 = df.loc({ columns: ['attnDate', 'attnTime', 'lvofTime', 'work_hours'] });
                    // df2.ctypes.print();
                    // df2.print();
                    let series = df2['work_hours'];
                    for (let i = 0; i < attendcardarr.length; i++) {
                        let item = attendcardarr[i];
                        item['work_hours'] = series.values[i];
                    }

                    df2 = df2.asType('attnDate', 'string');
                    // df2.ctypes.print();

                    let disp_series = df['attnDate'].apply((val) => {
                        if (val == null || val == '') return '';
                        let nday = dayjs(val.trim(), 'YYYYMMDD');
                        return nday.format('MM/DD');
                    });

                    df2 = df2.addColumn('workDate', disp_series);
                    // df2.print();

                    // df2.plot("plot_div").line( {
                    //   config: {
                    //     x: 'workDate',
                    //     y: 'lvofTime'
                    //   }
                    // });

                    console.log(df2.index.length);

                    let base = [];
                    for (let i = 0; i < df2.index.length; i++) {
                        base.push(1800);
                    }
                    let et = [];
                    for (let i = 0; i < df2.index.length; i++) {
                        et.push(2100);
                    }
                    let ot = [];
                    for (let i = 0; i < df2.index.length; i++) {
                        ot.push(2200);
                    }

                    console.log(base);
                    console.log(ot);
                    // df2['workDate'].print();
                    // df2["lvofTime"].print();

                    let df3 = new dfd.DataFrame(
                        {
                            'off time': df2['lvofTime'].values,
                            '18h': base,
                            '21h': et,
                            //'22h': ot
                        },
                        {
                            index: df2['workDate'].values,
                        }
                    );
                    // df3.print();
                    const layout = {
                        width: '100%',
                        height: '500px'
                    }
                    const config = {
                        columns: ['off time', '18h', '21h'],
                    }
                    df3.plot('plot_div').line({layout, config});
                })
                .catch((error) => {
                    // to do : error
                    console.error(error);
                });
        }
    }; //:

    const prevWeekDate = () => {
        let date = DateTimeUtils.getPrevWeekDate(prop_date);
        calDate = date;
        console.log(date);
        prop_date = date;
        doEvent();
    };

    const nextWeekDate = () => {
        let date = DateTimeUtils.getNextWeekDate(prop_date);
        console.log(date);
        prop_date = date;
        doEvent();
    };

    // init
    // 반응형으로 동작하도록
    $: {
        console.log('period:', DateTimeUtils.getStartAndEndDate(prop_date));

        period = DateTimeUtils.getStartAndEndDate(prop_date);
        // https://svelte.dev/tutorial/reactive-statements
        //console.log('pro_changed:', prop_date)
        getAtncards(prop_empId, prop_date);
    }
</script>

<!-- flex column -->
<div class="atcard-main">
    <!-- first column-->
    <div id="first">
        캘린더: <input id="cal" type="date" on:change={fn.setDate} value={calDate} />
        <button class="btn btn-primary" on:click={prevWeekDate}>이전주</button>
        <button class="btn btn-info" on:click={fn.setToday}>오늘</button>
        <button class="btn btn-info" on:click={nextWeekDate}>다음주</button>

    </div>
    <!-- second column-->
    <div id="second">
        <div>기간 : {period.monday} ~ {period.sunday}</div>
        <div id="inner" class="atcd-inner">
            <!-- left -->
            <div id="left" class="atcd-inner-left">
                <div class="overflow-x-auto">
                    <!-- table start-->
                    <table>
                        <tr>
                            <th>날짜</th>
                            <th>출근시간</th>
                            <th>퇴근시간</th>
                            <th>근무시간</th>
                        </tr>
                        {#if attendcardarr}
                            {#each attendcardarr as item}
                                <tr>
                                    <td class="center">{DateTimeUtils.format(item.attnDate, 'YYYY-MM-DD')} </td>
                                    <td class="center">{StringUtils.formatTime(item.attnTime)}</td>
                                    <td class="center">{StringUtils.formatTime(item.lvofTime)}</td>
                                    <td class="center">{item.work_hours}</td>
                                </tr>
                            {/each}
                        {/if}
                    </table>
                    <!-- table end -->
                </div>
            </div>
            <!-- right -->
            <div class="atcd-inner-right">
                <div id="plot_div" class="plot-div" />
            </div>
        </div>
    </div>
</div>

<style>
    .atcard-main {
        margin-left: 10px; 
        margin-top: 10px; 
        display: flex;
        flex-direction: column;
    }
    
    #first { 
        text-align: right; 
    }

    .atcd-inner {
        display: flex;
        flex-direction: row;
    }
    .atcd-inner-left {
        flex-grow: 0;
    }
    .atcd-inner-right { 
        flex-grow:1;
         margin: 5x;
    }

    .plot-div {
        width: 100%;
        height: 100%; 
        /*
        max-height: 500px;
        margin-left: 10px;
        width: 800px;
        max-width: 800px; */
        border: 1px solid #ccc;
    }
</style>
