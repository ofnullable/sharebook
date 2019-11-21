import styled from 'styled-components';

import device from '@styles/device';
import { COLOR_SCHEME } from '@styles/colors';

export const ReviewFormWrapper = styled.div`
  border-bottom: 1px solid ${COLOR_SCHEME.gray};
  padding: 20px;
  background-color: ${COLOR_SCHEME.lightGray};

  & p {
    margin: 0 0 1em;
    text-align: center;
  }

  & form {
    &::after,
    &::before {
      clear: both;
      content: '';
      display: block;
    }

    & textarea {
      border: 2px solid ${COLOR_SCHEME.gray};
      ${device.laptops`
        min-height: 120px;
        width: 90%;
        margin: 0 auto;
      `};
      ${device.tablets`
        min-height: 120px;
        width: 90%;
        margin: 0 auto;
      `};
      ${device.mobiles`
        min-height: 80px;
        width: 100%;
      `};
    }
  }

  & button {
    float: right;
    margin-right: 5%;
    & i {
      padding-right: 5px;
    }
  }
`;
