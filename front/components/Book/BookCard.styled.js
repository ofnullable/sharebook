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
  display: inline-block;
  color: initial;
  border-radius: 10px;
  border: 1px solid rgba(0, 0, 0, 0.125);
  background-color: ${COLOR_SCHEME.white};

  & img {
    width: 100%;
    border-top-left-radius: 10px;
    border-top-right-radius: 10px;
  }
`;

export const CardBody = styled.div`
  padding: 10px;
  & .card-title {
    ${device.laptops`
      font-size: 1.5em;
    `};
    ${device.tablets`
      font-size: 1.5em; 
    `};
    ${device.mobiles`
      font-size: 1.2em;
    `};
    margin: 0.5em;
  }
  & .description {
    margin: 0.5em;
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
