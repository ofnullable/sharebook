import styled from 'styled-components';

export const HeaderNav = styled.nav`
  text-align: center;
  & ul {
    padding: 0 16px;
    display: flex;
    justify-content: space-between;
    & li {
      display: flex;
      padding: 6px 8px;
    }
    & a {
      font-size: 1.5em;
      font-weight: bold;
    }
  }
`;
