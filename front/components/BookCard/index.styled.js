import styled from 'styled-components';

import device from '@styles/device';
import { COLOR_SCHEME } from '@styles/colors';

export const Card = styled.div`
  ${device.laptops`
    width: 18%;
    margin: 1%;
  `};
  ${device.tablets`
    width: 31%;
    margin: 1%;
  `};
  ${device.mobiles`
    width: 48%;
    margin: 1%;
  `};
  color: ${COLOR_SCHEME.black};
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
  text-align: right;
  & .card-title {
    text-align: left;
    font-size: 1rem;
    margin: 0.5em 0;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
  }
  & .description {
    text-align: left;
    margin: 0.5em 0;
    font-size: 0.9rem;
    color: ${COLOR_SCHEME.darkGray};
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
  }
  & span {
    font-size: 12px;
    color: ${COLOR_SCHEME.darkGray};
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
  }
`;
