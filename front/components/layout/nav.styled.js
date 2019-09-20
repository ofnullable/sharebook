import styled from 'styled-components';

import device from '@styles/device';
import { COLOR_SCHEME } from '@styles/colors';

export const HeaderNav = styled.nav`
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
  float: ${props => props._float};
  & a {
    display: inline-block;
    vertical-align: sub;
    color: inherit;
  }
`;

export const HeaderMenuGroup = styled.div`
  padding: 0;
  display: inline-block;
  vertical-align: middle;
  & span {
    padding: 0 10px;
    vertical-align: sub;
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
