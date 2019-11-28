import styled from 'styled-components';

import { BookDetailHeader } from '@components/BookPage/BookDetailPage/index.styled';
import { COLOR_SCHEME } from '@styles/colors';
import { Button } from '@styles/common';

export const RegisterForm = BookDetailHeader.withComponent('form');

export const BookImagePreview = styled.div`
  width: 200px;
  height: 300px;
  margin: 0 auto;
  color: ${COLOR_SCHEME.secondary};
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  border-radius: 15px;
  cursor: pointer;
  border: 2px dashed ${COLOR_SCHEME.secondary};

  &.uploaded {
    border: none;
    background: ${props => `url(${props._preview}) center center / cover no-repeat`};
  }

  & p {
    margin: 0.5em 0;
  }
`;

export const RegisterButton = styled(Button)`
  float: right;
`;
