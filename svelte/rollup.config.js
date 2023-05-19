import svelte from 'rollup-plugin-svelte';
import commonjs from '@rollup/plugin-commonjs';
import resolve from '@rollup/plugin-node-resolve';
import livereload from 'rollup-plugin-livereload';
import { terser } from 'rollup-plugin-terser';
import sveltePreprocess from 'svelte-preprocess';
import typescript from '@rollup/plugin-typescript';
//import css from 'rollup-plugin-css-only';
import path from "path";
import alias from "@rollup/plugin-alias";
import postcss from "rollup-plugin-postcss";
import autoprefixer from 'autoprefixer';
import cssimport from 'postcss-import'

const production = !process.env.ROLLUP_WATCH;

function serve() {
	let server;

	function toExit() {
		if (server) server.kill(0);
	}

	return {
		writeBundle() {
			if (server) return;
			server = require('child_process').spawn('npm', ['run', 'start', '--', '--dev'], {
				stdio: ['ignore', 'inherit', 'inherit'],
				shell: true
			});

			process.on('SIGTERM', toExit);
			process.on('exit', toExit);
		}
	};
}

export default {
	input: 'src/main.ts',
    output: {
        sourcemap: true,
        //format: 'iife',
        // minify할 것인지 여부, 개발 시에는 false
        //compact: false, 
        format: 'es',  // 동적 임포트 사용시
        // 동적으로 컴포넌트를 로드하고 번들 파일을 분리할 때 사용한다.
        inlineDynamicImports: true, 
        name: 'app',
        // file은 동적 컴포넌트 로드일때는 사용하지 못하고 dir을 사용한다. 
        //file: 'public/build/bundle.js',
        dir: 'public/build',
        entryFileNames: '[name]-[format].js',
        chunkFileNames: '[name]-[format]-[hash].js',
    },
	plugins: [
		svelte({
			preprocess: sveltePreprocess({ 
				sourceMap: !production,
				// svelte 컴포넌트에서 sass를 사용할 수 있도록 설정
				scss: {
				   //prependData: `@import 'src/styles/variables.scss';`
				},
				postcss: {
					plugins: [require('autoprefixer')()]
				}
			}),
			compilerOptions: {
				// enable run-time checks when not in production
				dev: !production
			},
		}),
        // we'll extract any component CSS out into
        // a separate file - better for performance
		postcss({
			// src 아래의 css 파일들 및 Svelte component 안의 css를 global.css로 생성 
            //includePaths: ['src/**/*'], // 이거는 의미가 없음
            extensions: ['.css', '.scss', '.sass'],  // 설정된 확장자로 끝나는 파일들을 처리,
            // bundle.js가 생성되는 디렉터리에 global.css 파일이 생성 된다.
            extract: 'global.css',  // true로 설정하면 bundle.css 파일이 생성된다. 
            inject: true,  // default: true, css를 bundle.js에 삽입한다.
            plugins: [cssimport(), autoprefixer()],  // PostCSS plugins
          }),
		// we'll extract any component CSS out into
		// a separate file - better for performance
		// css({ output: 'bundle.css' }),
		// 절대경로 alias 추가하기
		// 아래와 같이 설정시 @/components는 /src/components 경로로 실행
		alias({
			entries: [
				{
					find: "@",
					replacement: path.resolve(__dirname, "src")
				}
			]
		}),
		// If you have external dependencies installed from
		// npm, you'll most likely need these plugins. In
		// some cases you'll need additional configuration -
		// consult the documentation for details:
		// https://github.com/rollup/plugins/tree/master/packages/commonjs
		resolve({
			browser: true,
			dedupe: ['svelte']
		}),
		commonjs(),
		typescript({
			sourceMap: !production,
			inlineSources: !production
		}),

		// In dev mode, call `npm run start` once
		// the bundle has been generated
		!production && serve(),

		// Watch the `public` directory and refresh the
		// browser on changes when not in production
		!production && livereload('public'),

		// If we're building for production (npm run build
		// instead of npm run dev), minify
		production && terser()
	],
	watch: {
		clearScreen: false
	}
};
