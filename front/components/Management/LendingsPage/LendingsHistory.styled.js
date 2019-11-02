import styled from 'styled-components';

import { COLOR_SCHEME } from '@styles/colors';

export const ItemWrapper = styled.div`
  padding: 1em;
  border-bottom: 1px solid ${COLOR_SCHEME.gray};
`;

export const ThumbnailImage = styled.img`
  width: 15%;
  vertical-align: middle;
  margin-right: 5%;
`;

export const LendingInfoWrapper = styled.div`
  width: 55%;
  display: inline-block;
  vertical-align: middle;
  margin-right: 5%;
  & p {
    margin-top: 0.5em;
    font-weight: bolder;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
  }
`;

export const ButtonWrapper = styled.div`
  width: 20%;
  display: inline-block;
  vertical-align: middle;
  text-align: center;

  & button {
    padding: 1em;
    border-radius: 50%;
  }
`;
