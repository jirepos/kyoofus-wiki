<script lang="ts">
    import { Table } from 'sveltestrap';
    import { Button, Badge } from 'sveltestrap';
    import EmailQuery from '../../api/email/query/EmailQuery';
    import { IEmailQueryParams } from '../../api/email/query/EmailQuery';

    // libraries
    let query = new EmailQuery();

    // props
    export let targetEmail: string;
    export let inOutType: string;

    

    // 제목
    let title: string;
    // 이메일 목록
    let emails;

    $: if(inOutType == "in") {
        title = "받은 편지";
    } else {
        title = "보낸 편지";
    }


    $: if (targetEmail) {
        getEmails({
            inOutType: inOutType,
            fromOrTo: targetEmail,
            loginUserEmail: null,
            recvSendDt: null,
            beforeDt: true,
        });
    }

    const getEmails = (params: IEmailQueryParams) => {
        
        // 로그인 사용자와 동일한 사용자이면 처리하지 않음
        if (params.fromOrTo == 'sanghyun@naonsoft.com') return;
        query
            .getMyEmailsByFrom(params)
            .then((res) => {
                console.log(">>>>>>>> RESULT ");
                console.log(res.data.myEmailsByFrom);
                emails = res.data.myEmailsByFrom;
            })
            .catch((err) => {
                console.log(err);
            });
    }; // getEmails

    const nextButtonClicked = () => {
        if (emails && emails.length > 1) {
            debugger;
            let item = emails[emails.length - 1];
            console.log(item);
            //let mailrecvSendDt = emails[emails.length - 1].mailrecvSendDt;
            //getEmails(item.mailrecvSendDt, true);
            getEmails({
                inOutType: inOutType,
                fromOrTo: targetEmail,
                loginUserEmail: null,
                recvSendDt: item.mailRecvDt,
                beforeDt: true,
            });
        }
    }; // nextButtonClicked
    const firstBttonClicked = () => {
        getEmails({
            inOutType: inOutType,
            fromOrTo: targetEmail,
            loginUserEmail: null,
            recvSendDt: null,
            beforeDt: true,
        });
    }; // firstBttonClicked
    const prevButtonClicked = () => {
        if (emails && emails.length > 1) {
            let item = emails[0];
            //getEmails(item.mailrecvSendDt, false);
            getEmails({
                inOutType: inOutType,
                fromOrTo: targetEmail,
                loginUserEmail: null,
                recvSendDt: item.mailRecvDt,
                beforeDt: false,
            });
        }
    }; // prevButtonClicked
</script>



<div>
    <Badge href="#" color="primary">{title}</Badge>
    {#if emails}
        <Table responsive>
            <thead>
                <tr>
                    <th>제목</th>
                    <th>받은날짜</th>
                </tr>
            </thead>
            <tbody>
                {#each emails as item}
                    <tr>
                        <td>{item.subject}</td>
                        <td>{item.mailRecvDt}</td>
                    </tr>
                {/each}
            </tbody>
        </Table>
        <div>
            <Button primary on:click={firstBttonClicked}>처음</Button>
            <Button primary on:click={prevButtonClicked}>이전</Button>
            <Button primary on:click={nextButtonClicked}>다음</Button>
        </div>
    {/if}
</div>

