# Manage 프로젝트 


## 프로젝트 생성 
degit를 사용하여 프로젝트를 생성했다. 

```bash
npx degit sveltejs/template svelte 
cd svelte-app
```
## 시작하기 

```bash
cd svelte
npm install
```

로컬 서버 실행하기. 
```bash
npm run dev
```



## 프로젝트 구조 

```
📁manage  # project root 
  📁public  # public folder 
    📁build # build folder 
      📄bundle.js  # bundle 파일 
      📄bundle.js.map # map 파일 
      📄global.css # css 번들 파일 
    📄index.html  
  📁src
    📁store  # svelte store 
    📁views  # svelte component folder 
    📄_custom.scss # Sass 파일 
    📄App.svelte  # Svelte App 
    📄main.js # entry point 
    📄router.js # svelte router 
  📄.gitignore
  📄package.json
  📄rollup.config.js 
```


## package.json 

scripts 에서 npm을 사용할 때 
```json
  "scripts": {
    "build": "rollup -c",
    "dev": "rollup -c -w",
    "start": "sirv public -s --no-clear"
  },
```  
scripts 에서 npx를 사용할 때 

```json
  "scripts": {
    "build": "npx rollup -c",
    "dev": "npx rollup -c -w",
    "start": "npx serve -n -l 5000 public"
  },
```





rolllup.config.js에서 npx를 사용할 때에는 --dev를 제거해야 한다. 

```jsx

function serve() {
    let server;

    function toExit() {
        if (server) server.kill(0);
    }

    return {
        writeBundle() {
            if (server) return;
            // npm 사용할 때 
            //server = require('child_process').spawn('npm', ['run', 'start', '--', '--dev'], {
            // npx 사용할 때
            server = require('child_process').spawn('npm', ['run', 'start'], {
                stdio: ['ignore', 'inherit', 'inherit'],
                shell: true,
            });

            process.on('SIGTERM', toExit);
            process.on('exit', toExit);
        },
    };
}
```


## VSCode setting
.vscode 폴더아래에 lauhcn.json파일에 vmArgs에 spring.profiles.active를 추가한다. 
```json
{
  "configurations": [
    {
      "type": "java",
      "name": "Spring Boot-HrmApplication<hrm>",
      "request": "launch",
      "cwd": "${workspaceFolder}",
      "mainClass": "com.jirepo.HrmApplication",
      "projectName": "hrm",
      "vmArgs": "-Dspring.profiles.active=local",
      "args": "",
      "envFile": "${workspaceFolder}/.env"
    }
  ]
}
```











