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
  align-items: center;
  justify-content: center;
  border: 2px dashed ${COLOR_SCHEME.secondary};
  border-radius: 15px;
  cursor: pointer;
`;

export const ImageUploadButton = styled(Button)`
  width: 200px;
  margin: 1em calc((100% - 200px) / 2) 0;
`;

export const RegisterButton = styled(Button)`
  float: right;
`;
