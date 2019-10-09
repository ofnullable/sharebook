import styled from 'styled-components';

import device from '@styles/device';
import { transition } from '@styles/mixins';
import { COLOR_SCHEME } from '@styles/colors';

export const Title = styled.h1`
  margin: 0.7rem 1%;
  display: inline-block;
  vertical-align: middle;
`;

export const LeftMenu = styled.aside`
  ${device.laptops`
    width: 22%;  
    float: left;
    margin-right: 3%;
  `};
  ${device.tablets`
    width: 22%;
    float: left;
    margin-right: 3%;
  `};
  ${device.mobiles`
    position: fixed;
    left: 0;
    bottom: 0;
    width: 100%;
    margin: 0;
    padding: 5px;
    background-color: #f8f9fa;
  `};
  ${transition};
  & ul {
    margin: 0;
    padding: 0;
    text-align: left;
    list-style: none;
  }
`;

export const MenuItem = styled.a`
  ${device.laptops`
    display: block;
    text-align: left;
    padding: 15px;
  `}
  ${device.tablets`
    display: block;
    text-align: left;
    padding: 15px;
  `}
  ${device.mobiles`
    display: inline-block;
    text-align: center;
    width: 33.3333%;
    padding: 10px 0;
  `}
  color: ${COLOR_SCHEME.black};
  cursor: pointer;
  border-radius: 5px;
  &:hover {
    background-color: ${COLOR_SCHEME.lightGray};
  }
  & i {
    vertical-align: middle;
    margin-right: 5px;
  }
  & p {
    margin: 0;
    display: inline-block;
    vertical-align: middle;
  }
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
