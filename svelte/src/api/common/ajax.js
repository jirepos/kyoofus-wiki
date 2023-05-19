/**
 * fetch() 함수를 사용한 웹 요청과 응답을 처리한다. 
 */

/** Custom Error  */
class AjaxError {
  constructor(status, headers) {
    this.status = status;
    this.headers = headers; 
  }
}// AjaxError




/** contentType에 따른 반환값 가공 처리  */
const getContent = ( response ) => {
  
    let contentType = response.headers.get("Content-Type")
    if(contentType.indexOf("application/json") >= 0) {
        // console.log("response.json()")
        return response.json()
      }else if(contentType.indexOf("text/xml") >= 0) { 
        return response.text() 
      }else if(contentType.indexOf("text/plain") >= 0) { 
        return response.text() 
      }else if(contentType.indexOf("image/png") >= 0) { 
        return response.blob()
      }else if(contentType.indexOf("application/octet-stream") >= 0) { 
        return response.blob()
      }else {
        // application/xml은 여기서 처리함 
        return response.text()
      }
}// getContent() 



/** 에러 메시지 표시  */
const alertError = ( error ) => {
    switch(error.status) {
        case 500:
          alert("system error")
          break;
        case 409:
          alert(error.headers.get("X-Message-Code") + ":" + error.headers.get("X-Message"))
          break; 
        case 404:
          alert("잘못된 요청")
          break;
        case 403:
          alert("권한 없음")
          break;
        case 401:
          alert("로그인 후에 이용하여 주세요")
          break;
      }
}// alertError()




/**
 * API 요청을 처리한다. 다음과 같이 사용한다. 
 * 
 * public async getEmployees(): Promise<any>  {
 *     let optioons = {};
 *     return await = ajx(url, options);
 * }
 * 
 * @param {*} url 요청 URL 
 * @param {*} options 옵션 
 * @returns Promise<any> 반환 
 */
const ajax  = ( url, options ) =>{


    // 요청 흐름은 fetch() --> browser --> server 이 된다.
    // browser 는 OPTIONS 메소드로 서버에게 접근 가능한지 물어본다.(preflight 과정)
    // 이때 서버는 응답 헤더에 다음 값들을 설정할 수 있고
    // Access-Control-Allow-Origin: 접근 가능한 호스트
    // Access-Control-Allow-Methods: 접근 가능한 메소드
    // Access-Control-Allow-Headers: 접근 가능한 헤더
    // browser 는 이 허용된 값들 내에서 서버에 실제 요청을 하게 된다.

    // credentials: 'include' 옵션을 추가하면
    // 서버는 응답에 Access-Control-Allow-Origin 헤더와 함께 Access-Control-Allow-Credentials: true 헤더를 추가해서 응답해야 한다
    // 200 OK
    //Access-Control-Allow-Origin: https://server.com
    //Access-Control-Allow-Credentials: true

    // 자격 증명이 함께 전송되는 요청을 보낼 땐 Access-Control-Allow-Origin에 *을 쓸 수 없다.
    // 위 예시에서처럼 Access-Control-Allow-Origin엔 정확한 오리진 정보만 명시되어야 한다. 

    // mode: "no-cors"를 지정하면, 요청에 제한된 헤더만 포함할 수 있는 점을 주의하세요. 사용 가능한 헤더는 다음과 같습니다.
    // Accept
    // Accept-Language
    // Content-Language
    // Content-Type, 값으로는 application/x-www-form-urlencoded, multipart/form-data, 또는 text/plain
    // credentials: 'include'를 추가한 경우, Access-Control-Allow-Origin에 와일드카드를 사용할 수 없습니다


    let defaultOptions =  {
        credentials:  'include',   //'same-origin',    // 자격증명이 포함된 요청을 하려면 이 줄을 추가해야. 이 옵션이 없으면 쿠키 값을 서버로 보내지 않음 
        method: 'POST',  // GET, POST, PUT, DELETE, etc 
        mode:   'cors', //'no-cors',    // no-cors, cors, *same-origin // cors로 값을 설정해야 Content-Type의 값을 설정할 수 있음
        hache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
        //credentials: 'same-origin', // include, *same-origin, omit
        headers: {
            'Accept': 'application/json', // 클라이언트가 이해 가능한 컨텐츠 타입이 무엇인지 
            'Content-Type': 'application/json',  // 서버에 어떤 형식의 데이터를 보내는지 알려줌
            'Sec-Fetch-Site': 'cross-site'
            // 'Content-Type': 'application/x-www-form-urlencoded',
        },        
    }

     
    let opts = Object.assign(defaultOptions, options)
    if(opts.body) {
        if(!opts.isFileUpload) {       
            // file upload인 경우에만 사용 
            opts.body = JSON.stringify(opts.body)
        }
    }

    return new Promise ( (resolve, reject) => {
        fetch(url, opts)
        .then( res => {
            if(res.ok) { // res.ok 반드시 체크 
                let contentType = res.headers.get("Content-Type")
                //console.log(contentType);
                if(!contentType) {
                    return null;   // API 호출 시 값이 없으며 Content-Type이 없음 
                }
                return getContent(res);

            }else { 
                throw new AjaxError(res.status, res.headers);
            }

        })
        .then( res => { 
            resolve(res); // if response is okay 
        })
        .catch(error => {
            reject(error);
        })
    })
}// ajax()

export  { ajax,AjaxError };