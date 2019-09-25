import styled from 'styled-components';

import device from '@styles/device';
import { COLOR_SCHEME } from '@styles/colors';

export const BookDetailHeader = styled.article`
  ${device.laptops`
    width: 800px;
    padding: 50px 0;
  `};
  ${device.tablets`
    width: 90%;
    padding: 50px 0;
  `};
  ${device.mobiles`
    width: 100%;
    padding: 20px 0;
  `};
  margin: 0 auto;
  &::after,
  &::before {
    display: block;
    clear: both;
    content: '';
  }
`;

export const BookImageWrapper = styled.section`
  ${device.laptops`
    width: 40%;
  `};
  ${device.tablets`
    width: 40%;
  `};
  ${device.mobiles`
    width: 100%;
  `};
  display: inline-block;
  vertical-align: top;
  text-align: center;
  & img {
    width: 200px;
    height: 300px;
    border-radius: 5px;
    vertical-align: bottom;
  }
`;

export const BookDetailWrapper = styled.section`
  ${device.laptops`
    width: 60%;
  `};
  ${device.tablets`
    width: 60%;
  `};
  ${device.mobiles`
    width: 100%;
  `};
  display: inline-block;
  vertical-align: top;
  & button {
    ${device.laptops`
      width: 30%;
    `};
    ${device.tablets`
      width: 50%;
    `};
    ${device.mobiles`
      width: 100%;
    `};
  }
`;

export const BookInfoWrapper = styled.div`
  & h1 {
    ${device.laptops`
      font-size: 3em;
    `};
    ${device.tablets`
      font-size: 2.5em;
    `};
    margin: 0 0 0.25em;
  }
  & p {
    margin: 0 0 0.5em;
  }
  & span {
    color: ${COLOR_SCHEME.darkGray};
  }
  & div {
    margin: 0.5em 0;
    &:first-child {
      margin-top: 0;
    }
  }
`;
