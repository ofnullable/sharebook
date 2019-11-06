import styled from 'styled-components';

export const ReviewFormWrapper = styled.div`
  & p {
    text-align: center;
  }
  & form {
    &::after,
    &::before {
      clear: both;
      content: '';
      display: block;
    }
  }
  & button {
    float: right;
    margin-right: 5%;
  }
`;
