import styled from 'styled-components';

import device from '@styles/device';
import { COLOR_SCHEME } from '@styles/colors';

export const HeaderNav = styled.nav`
  text-align: center;
  & ul {
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
    display: flex;
    justify-content: space-between;
    background-color: ${COLOR_SCHEME.primary};
    & li {
      color: ${COLOR_SCHEME.white};
      display: flex;
      padding: 6px 8px;
      align-items: center;
    }
    & a {
      color: inherit;
      vertical-align: middle;
    }
    & div {
      display: flex;
    }
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
      font-size: 1.2em;
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
      display: none;
    }
    & i {
      font-size: 1.5em;
      vertical-align: middle;
    }
  `}
  color: white;
  cursor: pointer;
`;

export const Homepage = HomepageLink.withComponent('div');
