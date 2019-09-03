import styled from 'styled-components';
import device from '../device';
import { COLOR_SCHEME } from '../colors';

export const SignUpForm = styled.form`
  ${device.laptops`
    width: 500px;
  `}
  ${device.mobiles`
    width: 100%;
  `}
  margin: 0 auto;
  overflow: auto;
  & label > span {
    color: ${COLOR_SCHEME.red};
  }
`;
