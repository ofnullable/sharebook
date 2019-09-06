import styled from 'styled-components';

import device from '@styles/device';
import { COLOR_SCHEME } from '@styles/colors';

export const Card = styled.div`
  ${device.laptops`
    width: 31%;
    margin: 1.15%;
  `};
  ${device.tablets`
    width: 48%;
    margin: 1%;
  `};
  ${device.mobiles`
    width: 48%;
    margin: 1%;
  `};
  color: initial;
  text-align: left;
  display: inline-block;
  border-radius: 5px;
  border: 1px solid rgba(0, 0, 0, 0.125);
  background-color: ${COLOR_SCHEME.white};

  & img {
    width: 100%;
    border-top-left-radius: 5px;
    border-top-right-radius: 5px;
  }
`;

export const CardBody = styled.div`
  padding: 10px;
  & .card-title {
    ${device.laptops`
      font-size: 1.7em;
    `};
    ${device.tablets`
      font-size: 1.5em; 
    `};
    ${device.mobiles`
      font-size: 1.2em;
    `};
    margin: 0.5em 0;
  }
  & .description {
    margin: 0.5em 0;
    color: ${COLOR_SCHEME.border};
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
  }
  & span {
    float: right;
    font-size: 12px;
    margin: 0.5em;
    color: ${COLOR_SCHEME.border};
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
  }
  &::after,
  &::before {
    display: block;
    content: '';
    clear: both;
  }
`;
