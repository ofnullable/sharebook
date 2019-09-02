import styled from 'styled-components';
import device from '../device';

export const SignInForm = styled.form`
  ${device.laptops`
    width: 500px;
  `}
  ${device.mobiles`
    width: 100%;
  `}
  margin: 0 auto;
  overflow: auto;
`;
