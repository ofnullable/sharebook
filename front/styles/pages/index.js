import styled from 'styled-components';

import device from '@styles/device';

export const CardWrapper = styled.section`
  ${device.tablets`
    max-width: 720px;
    margin: 0 auto;
  `}
`;
