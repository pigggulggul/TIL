# WebPpack

## Webpack React + TS 세팅

- <a href="https://github.com/qufvkdlej/WebpackReactTs">Webpact React+TS</a>

<aside>
💡 Webpack의 작동 원리에 대하여 다루고있습니다.

</aside>

## 👀 Bundler - Webpack

> .js, .sass, css, jpg 등을 하나하나의 모듈로보고 이 모듈들을 배포용으로 병합하고 포장하는 작업을 Bundling이라고 하는데 이러한 번들링 작업을 수행하는 툴을 Bundler라고 한다. 그리고 Bundler 중 가장 인기가 많은 툴이다.

<br/>
파일의 종류가 많아지면 접속하는 유저의 입장에서 각각 다운로드가 되어 불편하고 오래걸린다. 그래서 수 많은 스크립트를 합치고 css를 합치다보면 브라우저 입장에서 다운로드 할 수가 줄어들기 때문에 속도가 향상된다.  또한 크로스 브라우징 할 때 호환이 안 되는 기능을 컨트롤 할 수 있고 난독화하여 코드를 바꿀 수 있다.
<br/><br/>
기본 사용 방법 : <br/>npm install —save-dev webpack webpack-cli webpack-dev-server html-webpack-plugin
<br/><br/>
설치를 하면 package.json에 devDependencies에 저장이 된다.

---

## 💭 webpack.config.json - 기본적인 번들링

```jsx
const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");

//require은 node.js에서 외부의 파일을 불러오는 방법이다.
module.exports = {
  //   entry: "./src/index.js",
  mode: "development",
  entry: {
    index: "./src/index.js",
  },
  output: {
    path: path.resolve(__dirname, "dist"),
    filename: "[name].js", //index.js
  },
  devServer: {
    static: "./dist",
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: "./src/index.html", //번들전 html
      filename: "./index.html", //번들후 html
      hasg: true, //모든스크립트, css 캐시 무효화
      showErrors: true, //오류 html에 출력
    }),
  ],
};
```

path 기능은 경로를 조금 더 쉽고 다양하게 활용 할 수 있게 활용한 것이다.

resolve : 합치되, 상위경로와 하위경로로 들어간 주소까지 합치는 함수.

path: path.resolve(\_\_dirname, "dist")

\_\_dirname/dist 폴더 뒤에 번들이 만들어진다.

HtmlWebpackPlugin은 html을 번들로 만들어준다.

```json
"scripts": {
    "build": "webpack",
    "start": "webpack server --open"
  },
```

package.json에서 build와 start를 수정해서 편하게 터미널에서 npm start 등으로 시작 할 수 있게 한다. npm start로 먼저 확인하고 이상 없으면 npm run build를 한다.

빌드가 끝나면 dist폴더가 생기고 번들링이 완료한다.

---

## 💭 webpack.config.json - CSS,SCSS 번들링

npm install —save-dev style-loader css-loader sass-loader sass

```jsx
module: {
    rules: [
      {
        test: /\.s[ac]ss$/i, //정규 표현식으로 확장자가 css로 끝나는 파일 선택
        use: ["style-loader", "css-loader", "sass-loader"],
        //style-loader를 이용하여 style태그 안에 넣어준다.
      },
      {
        test: /\.css$/i,
        use: ["style-loader", "css-loader"],
      },
    ],
  },
```

이런식으로 plugins부분 뒤에 모듈을 추가하여 css와 scss를 번들링 할 수 있다.

css를 별도의 파일로 분리해주는 플러그인 - MiniCssExtractPlugin

npm install —save -dev mini-css-extract-plugin

MiniCssExtractPlugin을 plugins과 modue에 추가해줘야한다.

## 💭 webpack.config.json - 파일 이름 변경시

<br/>
dist파일에는 빌드 할 순수한 파일들만 있어야한다. 기본상태로 작업하다 이름을 바꾸다보면 dist 파일에는 바뀌기 전과 바뀐 후의 파일이 모두 있고 우리는 이러한 현상이 안 나오도록 바뀌기전 파일을 지워줘야한다.
<br/><br/>
npm install —save-dev Clean-wepack-plugin
<br/><br/>
CleanWebpackPlugin을 plugins에 추가해줘야한다.
<br/><br/>

## CSS, SCSS와 CleanWebpackPlugin까지 적용한 setting

```jsx
const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const { CleanWebpackPlugin } = require("clean-webpack-plugin");

//require은 node.js에서 외부의 파일을 불러오는 방법이다.
module.exports = {
  //   entry: "./src/index.js",
  mode: "development", // development (개발) production (최종) none
  entry: {
    // index: "./src/index.js",
    index: path.join(__dirname, "src", "index.js"),
    about: path.join(__dirname, "src", "about.js"),
  },
  output: {
    path: path.resolve(__dirname, "dist"),
    filename: "./js/[name].js", //js/index.js
  },
  devServer: {
    static: "./dist",
  },
  plugins: [
    new CleanWebpackPlugin(), //웹팩실행(build)마다 dist 청소
    new HtmlWebpackPlugin({
      template: "./src/index.html", //번들전 html
      filename: "./index.html", //번들후 html
      hasg: true, //모든스크립트, css 캐시 무효화
      showErrors: true, //오류 html에 출력
      chunks: ["index"],
    }),
    new HtmlWebpackPlugin({
      template: "./src/about.html",
      filename: "./about.html",
      hasg: true,
      showErrors: true,
      chunks: ["about"],
    }),
    new MiniCssExtractPlugin({
      filename: "./css/[name].css",
    }),
  ],
  module: {
    rules: [
      {
        test: /\.s[ac]ss$/i, //정규 표현식으로 확장자가 css로 끝나는 파일 선택
        use: [MiniCssExtractPlugin.loader, "css-loader", "sass-loader"],
        //style-loader를 이용하여 style태그 안에 넣어준다.
        //하지만 별도의 css,scss를 생성해주기 위해 MiniCssExtractPlugin추가
        exclude: /node_modules/,
        //node_modules 안의 파일들은 검사하지 말라는 명령어
      },
      {
        test: /\.css$/i,
        use: [MiniCssExtractPlugin.loader, "css-loader"],
        exclude: /node_modules/,
      },
    ],
  },
};
```
