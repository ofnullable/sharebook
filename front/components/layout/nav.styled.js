import styled from 'styled-components';

import device from '@styles/device';
import { COLOR_SCHEME } from '@styles/colors';

export const HeaderNav = styled.nav`
  text-align: center;
  & > ul {
    ${device.laptops`
      padding: 15px;
    `}
    ${device.tablets`
      padding: 12px;
    `}
    ${device.mobiles`
      padding: 10px;
    `}
    margin: 0;
    width: 100%;
    display: inline-block;
    background-color: ${COLOR_SCHEME.primary};
  }
`;

export const HeaderMenu = styled.li`
  color: ${COLOR_SCHEME.white};
  display: inline-block;
  padding: 6px 8px;
  &:last-child {
    float: right;
    & a {
      ${device.laptops`
        line-height: 1.5;
      `}
      ${device.tablets`
        line-height: 1.3;
      `}
    }
  }
  &:first-child {
    float: left;
  }
  & a {
    display: inline-block;
    vertical-align: middle;
    color: inherit;
  }
`;

export const HeaderMenuGroup = styled.div`
  padding: 0;
  display: inline-block;
  vertical-align: middle;
  & span {
    padding: 0 10px;
    ${device.laptops`
      line-height: 1.5;
    `}
    ${device.tablets`
      line-height: 1.3;
    `}
  }
`;

export const HomepageLink = styled.a`
  ${device.laptops`
    & span, & i {
      font-size: 1.5em;
    }
    & span {
      vertical-align: middle;
    }
    & i {
      vertical-align: middle;
      padding-right: .3em;
    }
  `}
  ${device.tablets`
    & span, & i {
      font-size: 20px;
    }
    & span {
      vertical-align: middle;
    }
    & i {
      vertical-align: middle;
      padding-right: .3em;
    }
  `}
  ${device.mobiles`
    & span {
      font-size: 18px;
      vertical-align: middle;
    }
    & i {
      display: none;
    }
  `}
  color: white;
  cursor: pointer;
`;

export const Homepage = HomepageLink.withComponent('div');
