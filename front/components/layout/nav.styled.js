import styled from 'styled-components';

import device from '@styles/device';
import { COLOR_SCHEME } from '@styles/colors';

export const HeaderNav = styled.nav`
  width: 100%;
  background-color: ${COLOR_SCHEME.primary};
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
    margin: 0 auto;
  }
`;
export const HamburgerMenu = styled.li`
  ${device.laptops`
    display: none;
  `};
  ${device.tablets`
    display: none;
  `};
  ${device.mobiles`
    display: inline-block;
    vertical-align: middle;
  `};
  padding: 5px;
  cursor: pointer;
  & i {
    display: block;
    color: ${COLOR_SCHEME.white};
  }
`;

export const HeaderMenu = styled.li`
  color: ${COLOR_SCHEME.white};
  display: inline-block;
  vertical-align: middle;
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
  color: white;
  cursor: pointer;

  ${device.laptops`
    & span, & i {
      font-size: 1.5em;
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
    & i {
      vertical-align: middle;
      padding-right: .3em;
    }
  `}
  ${device.mobiles`
    & span {
      font-size: 18px;
    }
    & i {
      display: none;
    }
  `}
  & span {
    display: inline-block;
    vertical-align: middle;
  }
`;

export const Homepage = HomepageLink.withComponent('div');
