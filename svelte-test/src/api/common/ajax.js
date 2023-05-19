/**
 * fetch() 함수를 사용한 웹 요청과 응답을 처리한다. 
 */


/** Custom Error  */
let AjaxError = function( status, headers) {
    this.status = status;
    this.headers = headers; 
}// AjaxError



/** contentType에 따른 반환값 가공 처리  */
const getContent = ( response ) => {
    if(contentType.indexOf("application/json") >= 0) {
        console.log("res.json()")
        return res.json()
      }else if(contentType.indexOf("text/xml") >= 0) { 
        return res.text() 
      }else if(contentType.indexOf("text/plain") >= 0) { 
        return res.text() 
      }else if(contentType.indexOf("image/png") >= 0) { 
        return res.blob()
      }else if(contentType.indexOf("application/octet-stream") >= 0) { 
        return res.blob()
      }else {
        // application/xml은 여기서 처리함 
        return res.text()
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

    let defaultOptions =  {
        credentials:  'include',   //'same-origin',    // 자격증명이 포함된 요청을 하려면 이 줄을 추가해야. 이 옵션이 없으면 쿠키 값을 서버로 보내지 않음 
        method: 'POST',  // GET, POST, PUT, DELETE, etc 
        mode:   'cors', //'no-cors',    // no-cors, cors, *same-origin // cors로 값을 설정해야 Content-Type의 값을 설정할 수 있음
        chache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
        //credentials: 'same-origin', // include, *same-origin, omit
        headers: {
            'Accept': 'application/json', // 클라이언트가 이해 가능한 컨텐츠 타입이 무엇인지 
            'Content-Type': 'application/json',  // 서버에 어떤 형식의 데이터를 보내는지 알려줌
            // 'Content-Type': 'application/x-www-form-urlencoded',
        },        
    }

     
    let opts = Object.assign(defaultOptions, options)
    if(opts.body) {
        if(!opts.isFileUpload) {       
            // file upload인 경우에만 사용 
            reqOptions.body = JSON.stringify(opts.body)
        }
    }

    return new Promise ( (resolve, reject) => {
        fetch(url, opts)
        .then( res => {
            if(res.ok) { // res.ok 반드시 체크 
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

export  { ajax };