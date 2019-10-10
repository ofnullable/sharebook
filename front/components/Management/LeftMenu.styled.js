import styled from 'styled-components';

import device from '@styles/device';
import { transition } from '@styles/mixins';
import { COLOR_SCHEME } from '@styles/colors';

export const MenuWrapper = styled.aside`
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
