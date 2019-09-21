import styled from 'styled-components';
import { COLOR_SCHEME } from '@styles/colors';

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

export const MenuItem = styled.li`
  cursor: pointer;
  padding: 15px;
  margin-bottom: 5px;
  border-radius: 5px;
  &:hover {
    background-color: ${COLOR_SCHEME.lightGray};
  }
`;

export const WithLeftMenu = styled.div`
  width: 75%;
  float: left;
`;
