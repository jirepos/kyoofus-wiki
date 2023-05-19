import { ajax } from '../../common/ajax';
import { GraphQLQueryBase } from '../../common/GraphQLQueryBase';


/** 받은 편지 보낸 편지 구분 열거형 */
export enum InOut {
    /** 받은 편지 */
    in = "in",
    /** 보낸 편지 */
    out = "out"
}


/**
 * 메일 조회 파라미터 인터페이스.
 */
export interface IEmailQueryParams {
    /** 보낸 메일/받은 메일 구분(in/out) */
    inOutType: string;
    /** 보낸사람 또는 받는 사람  */
    fromOrTo: string;
    /** 내 메일 주소  */
    loginUserEmail: string;
    /** 메일 수신/발송 일시 */
    recvSendDt: string;
    /** 메일 수신일시 이전 */
    beforeDt: boolean; 
}



export default class EmailQuery extends GraphQLQueryBase {
    /** default constructor  */
    constructor() {
        super();
    }

    /**
     * 특정 사용자에게 보낸 메일/받은 메일 목록을 조회한다. 
     * @param emailQueryArgs 
     * @returns 메일목록 
     */
    public async getMyEmailsByFrom(params:IEmailQueryParams): Promise<any> {
        const query = `query MY_EMAILS_BY_FROM($input: EmailSearchInput) {
            myEmailsByFrom(input: $input) {
              mailId
              msgId
              subject
              mailSendDt
              mailRecvDt
            }
          }`;
        const variables = {
            input: {
                inOutType: params.inOutType,
                beforeDt: params.beforeDt,
                recvSendDt: params.recvSendDt,
                loginUserEmail: params.loginUserEmail,
                fromOrTo: params.fromOrTo
            },
        };
        return await ajax(this.endPoint, this.getOptions(query, variables));
    } //:
}


