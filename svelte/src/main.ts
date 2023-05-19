import App from './App.svelte';

//Compiled CSS
import 'bootstrap/dist/css/bootstrap.min.css';
import "bootstrap";
// site css 
import "../static/site.css";


// web font 적용 URl 
// https://developers-kr.googleblog.com/2015/10/noto.html
const app = new App({
	target: document.body,
	// props: {
	// 	name: 'world'
	// }
});

export default app;