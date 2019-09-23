import styled from 'styled-components';

import device from '@styles/device';

export const ConditionWrapper = styled.div`
  ${device.laptops`
    max-width: 60%;
  `}
  ${device.tablets`
    max-width: calc(720px - 2%);
  `}
  ${device.mobiles`
    width: 100%;
  `}
  margin: 1em auto;
`;
