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
    display: none;
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
  display: block;
  text-align: left;
  color: ${COLOR_SCHEME.black};
  cursor: pointer;
  padding: 15px;
  border-radius: 5px;
  &:hover {
    background-color: ${COLOR_SCHEME.lightGray};
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

export const BottomMenuBar = styled.menu`
  display: none;
  ${device.mobiles`
    display: block;
  `}
  position: fixed;
  left: 0;
  bottom: 0;
  width: 100%;
  margin: 0;
  padding: 5px;
  background-color: ${COLOR_SCHEME.white};
`;

export const BottomMenuItem = styled.div`
  display: inline-block;
  text-align: center;
  width: 33.3333%;
  padding: 5px 0;
  border-radius: 5px;

  & > i {
    display: none;
    ${device.mobiles`
    display: block;
  `}
  }
  & > p {
    margin: 0;
  }
`;
