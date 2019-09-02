import Document, { Main, NextScript } from 'next/document';
import { ServerStyleSheet } from 'styled-components';

const title = 'public-share';
const description = 'share your books!';

export default class CustomDocument extends Document {
  static async getInitialProps(ctx) {
    const sheet = new ServerStyleSheet();
    const page = ctx.renderPage;
    try {
      ctx.renderPage = () =>
        page({
          enhanceApp: App => props => sheet.collectStyles(<App {...props} />),
        });
      const initialProps = await Document.getInitialProps(ctx);
      return {
        ...initialProps,
        styles: (
          <>
            {initialProps.styles}
            {sheet.getStyleElement()}
          </>
        ),
      };
    } finally {
      sheet.seal();
    }
  }

  render() {
    return (
      <html lang='ko'>
        <head>
          <meta charSet='utf-8' />
          <title>public-share</title>
          <meta name='viewport' content='width=device-width, initial-scale=1.0' />
          <meta httpEquiv='X-UA-Compatible' content='IE=edge,chrome=1' />
          <meta name='title' content={title} />
          <meta name='description' content={description} />
          <link rel='icon' href='/static/favicon.ico' />
          <link href='https://fonts.googleapis.com/icon?family=Material+Icons' rel='stylesheet' />
          <link
            href='https://fonts.googleapis.com/css?family=Do+Hyeon|Fira+Mono:500&display=swap&subset=korean'
            rel='stylesheet'
          />
        </head>
        <body>
          <Main />
          <NextScript />
        </body>
      </html>
    );
  }
}
