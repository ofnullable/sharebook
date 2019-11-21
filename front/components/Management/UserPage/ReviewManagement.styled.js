import styled from 'styled-components';

export const BookTitle = styled.a`
  color: initial;
  padding: 0 10px;
  & > h2 {
    display: inline-block;
    margin: 0.5em 0 0;
  }
  & > h2 + span {
    margin-left: 0.5em;
  }
`;
