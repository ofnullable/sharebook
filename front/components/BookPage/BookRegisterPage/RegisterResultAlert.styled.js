import styled from 'styled-components';

import { COLOR_SCHEME } from '@styles/colors';

export const Alert = styled.div`
  position: absolute;
  top: 10%;
  left: 5%;
  opacity: 0.7;
  width: 90%;
  padding: 20px;
  color: ${COLOR_SCHEME.white};
  background-color: ${props => (props._color ? COLOR_SCHEME[props._color] : COLOR_SCHEME.gray)};
  z-index: 10;
  border-radius: 5px;

  & span {
    font-size: 16px;
    display: inline-block;
    vertical-align: middle;
    padding-right: 5px;
  }
  & a {
    display: inline-block;
    vertical-align: middle;
    line-height: 0;
    & i {
      font-size: 20px;
      color: ${COLOR_SCHEME.white};
    }
  }
  & > i {
    cursor: pointer;
    float: right;
    vertical-align: middle;
    font-size: 20px;
    color: ${props => (props._color ? COLOR_SCHEME[props._color] : COLOR_SCHEME.gray)};
    background-color: white;
    border-radius: 50%;
  }
`;
