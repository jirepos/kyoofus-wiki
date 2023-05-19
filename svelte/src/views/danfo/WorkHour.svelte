<script>
    import dayjs from 'dayjs' 
    import { onMount } from "svelte";



    let obj_data = {
        attn_start : ['20220614 1010', '20220614 1020', '20220614 0930', ''],
        attn_end : ['20220614 1110', '20220614 1850', '', '20220614 2030']
    };
    let df = new dfd.DataFrame(obj_data);
    df.print();

    // for testing
    let tDate = dayjs("20220614 1010", "YYYYMMDD HHmm");
    let year = tDate.get("date");

    // functions 
    const toDateTime = (val) => {
        if(val == "") return "";
        return dayjs(val, "YYYYMMDD HHmm");
    }
    // 문자열을 값으로 가지는 Series를 합치기 
    const combine = (s1, s2) => { 
        let s3 = s1.str.concat(' ', 1);
        let s4 = s3.str.concat(s2.values,1);
        return s4.values;
    }
    const diffTime = (row) => { 
        if(row[2] == "" || row[3] == "") return "";
        let date = row[3].diff(row[2] ,"day");
        let hour = row[3].diff(row[2] ,"hour");
        let minute = row[3].diff(row[2] ,"minute") % 60;
        return date + " days," + hour + " hours," + minute + " minutes";
    }
        
    df.addColumn("start", combine(df['attnDate'], df['attnTime']), { inplace: true});
    //df.addColumn("end", df['attn_end'].values.map(toDateTime), { inplace: true});
    //df.addColumn("work_hours", df.apply(diffTime), { inplace: true});
    df.print();

    
    // life cycle
    onMount(async () => {
      df.plot('table').table();
      //df.plot("table").line()
    });

</script>

<h1>{year}</h1>
<div id="table"></div>