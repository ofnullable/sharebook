import styled from 'styled-components';
import { COLOR_SCHEME } from '@styles/colors';

export const Title = styled.h1`
  display: inline-block;
  margin: 0.7rem 1%;
`;

export const LeftMenu = styled.aside`
  width: 22%;
  margin-right: 3%;
  float: left;
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
  width: 75%;
  float: left;
  text-align: left;
`;
