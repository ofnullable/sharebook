import styled from 'styled-components';
import device from './device';

export const SignInFormWrapper = styled.div`
  text-align: center;
`;

export const SignInForm = styled.form`
  ${device.desktops`
    width: 500px;
  `}
  margin: 0 auto;
  text-align: left;
`;
