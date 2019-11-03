import styled from 'styled-components';

import device from '@styles/device';
import { COLOR_SCHEME } from '@styles/colors';

export const Title = styled.h1`
  margin: 0.7rem 1%;
  display: inline-block;
  vertical-align: middle;
`;

export const WithLeftMenu = styled.div`
  ${device.laptops`
    width: 75%;
    float: left;
    text-align: left;
  `};
  ${device.tablets`
    width: 75%;
    float: left;
    text-align: left;
  `};
  ${device.mobiles`
    width: 100%;
    text-align: left;
    margin-bottom: 55px;
  `};
`;

export const SubMenu = styled.div`
  margin: 0.7rem 1%;
  & span {
    margin-right: 1em;
    color: ${COLOR_SCHEME.gray};
    cursor: pointer;
    &.active {
      color: ${COLOR_SCHEME.black};
    }
  }
`;
