import React from 'react';

import { loadBookListRequest } from '@redux/actions/bookActions';

const Home = () => (
  <div>
    <div className='hero'>
      <h1 className='title'>Welcome to Public Share!</h1>

      <div className='row'>
        <button className='card'>
          <h3>Getting Started &rarr;</h3>
          <p>Learn more about Next.js on GitHub and in their examples.</p>
        </button>
        <button className='card'>
          <h3>Examples &rarr;</h3>
          <p>Find other example boilerplates on the Next.js GitHub.</p>
        </button>
        <button className='card'>
          <h3>Create Next App &rarr;</h3>
          <p>Was this tool helpful? Let us know how we can improve it!</p>
        </button>
      </div>
    </div>

    <style jsx>{`
      .hero {
        width: 100%;
        color: #333;
      }
      .title {
        margin: 0;
        width: 100%;
        padding-top: 1em;
        line-height: 1.15;
        font-size: 48px;
      }
      .title,
      .description {
        text-align: center;
      }
      .row {
        max-width: 880px;
        margin: 80px auto 40px;
        display: flex;
        flex-direction: row;
        justify-content: space-around;
      }
      .card {
        padding: 18px 18px 24px;
        width: 220px;
        text-align: left;
        text-decoration: none;
        color: #434343;
        border: 1px solid #9b9b9b;
      }
      .card h3 {
        margin: 0;
        font-size: 18px;
      }
      .card p {
        margin: 0;
        padding: 12px 0 0;
        font-size: 14px;
        color: #333;
      }
    `}</style>
  </div>
);

Home.getInitialProps = async ctx => {
  ctx.store.dispatch(loadBookListRequest());
};

export default Home;
