import styled from 'styled-components';

import device from '@styles/device';
import { transition } from '@styles/mixins';
import { COLOR_SCHEME } from '@styles/colors';

export const Title = styled.h1`
  display: inline-block;
  margin: 0.7rem 1%;
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
    position: absolute;
    width: 60%;
    height: 100vh;
    top: 0;
    left: 0;
    padding: 15px;
    background-color: white;
    transform: translateX(-100%);
    z-index: 10;
    box-shadow: rgba(0, 0, 0, 0.15) 0px 2px 10px;
    &.active {
      transform: translateX(0%);

      & + div {
        display: block;
      }
    }
  `};
  ${transition};
  & ul {
    margin: 0;
    padding: 0;
    text-align: left;
    list-style: none;
  }
`;

export const MenuCloseArea = styled.div`
  display: none;
  z-index: 9;
  position: absolute;
  width: 100%;
  height: 100vh;
  top: 0;
  left: 0;
  background-color: ${COLOR_SCHEME.gray};
  opacity: 0.7;
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
