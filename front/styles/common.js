import styled, { createGlobalStyle, keyframes } from 'styled-components';
import device from './device';
import { transition } from './mixins';
import { normalize } from './nomalize';
import { COLOR_SCHEME } from './colors';

const buttonColor = props => `
  color: ${props._color ? COLOR_SCHEME.white : COLOR_SCHEME.primary};
  border: 1px solid ${COLOR_SCHEME[`${props._color}`] || 'white'};
  background-color: ${COLOR_SCHEME[`${props._color}`] || 'white'};
`;

export const Button = styled.button`
  ${transition};
  ${buttonColor};
  cursor: pointer;
  padding: 3px 15px;
  border-radius: 5px;
  text-align: center;
  vertical-align: middle;
`;

export const ButtonLink = Button.withComponent('a');

const spin = keyframes`
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(359deg);
  }
`;

export const SpinIcon = styled.i`
  animation: ${spin} 1s infinite linear;
  font-size: ${props => props._size};
  color: ${props => COLOR_SCHEME[`${props._color}`] || 'inherit'};
  vertical-align: middle;
`;

export const CardWrapper = styled.section`
  ${device.tablets`
    max-width: 720px;
    margin: 0 auto;
  `}
`;

export const CenterDiv = styled.div`
  padding-top: 15px;
  text-align: center;
  &::after,
  &::before {
    content: '';
    clear: both;
    display: block;
  }
`;

export const RightDiv = styled.div`
  padding-top: 15px;
  text-align: right;
`;

export const LeftDiv = styled.div`
  padding-top: 15px;
  text-align: left;
`;

export const CenterForm = styled.form`
  ${device.laptops`
    max-width: 500px;
  `}
  ${device.tablets`
    max-width: 350px;
  `}
  ${device.mobiles`
    width: 100%;
  `}
  margin: 0 auto;
`;

export const InputGroup = styled.div`
  padding-bottom: 15px;
  & > label .import {
    color: ${COLOR_SCHEME.red};
  }
  & label + select {
    display: inline-block;
    vertical-align: middle;
    margin-left: 0.5em;
    padding: 5px 8px;
    border-radius: 5px;
    border: 1px solid ${COLOR_SCHEME.gray};
  }
  & label + textarea {
    display: block;
    width: 100%;
    padding: 5px;
    border-radius: 5px;
    border: 1px solid ${COLOR_SCHEME.gray};
    resize: vertical;
    overflow: auto;
  }
`;

export const ScreenOverlay = styled(CenterDiv)`
  position: fixed;
  opacity: 0.5;
  height: 100vh;
  width: 100vw;
  top: 0;
  left: 0;
  z-index: 1001;
  background-color: ${COLOR_SCHEME.lightGray};
`;

export const Modal = styled.div``;

export const LoadingIconWrapper = styled.div`
  position: fixed;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100vw;
  top: 0;
  left: 0;
  z-index: 1002;
`;

export const GlobalStyle = createGlobalStyle`
  ${normalize}
  html {
    ${device.laptops`
      font-size: 16px;
    `};
    ${device.tablets`
      font-size: 16px; 
    `};
    ${device.mobiles`
      font-size: 14px;
    `};
    color: ${COLOR_SCHEME.black};
  }
  .container {
    display: block;
    ${device.laptops`
      max-width: 1080px;
      margin: 0 auto;
    `}
    ${device.tablets`
      padding: 0 15px;
    `}
    ${device.mobiles`
      padding: 0 15px;
    `}
  }
  ul {
    padding: 0;
  }
  h1 {
    ${device.laptops`
      font-size: 2em;
    `};
    ${device.tablets`
      font-size: 1.7em;
    `};
    ${device.mobiles`
      font-size: 1.5em;
    `};
    &.title {
      ${device.laptops`
        font-size: 3em;
      `};
      ${device.tablets`
        font-size: 2.5em;
      `};
      width: 100%;
      margin: 0;
      padding-top: 1em;
      text-align: center;
    }
  }
  form {
    text-align: left;
  }
  input {
    width: 100%;
    padding: 5px 10px;
    border-radius: 5px;
    border: 1px solid ${COLOR_SCHEME.gray};
    &:read-only {
      background: ${COLOR_SCHEME.lightGray};
    }
  }
  img {
    display: inline-block;
    max-width: 100%;
  }
  ::selection {
    color: ${COLOR_SCHEME.white};
    background-color: ${COLOR_SCHEME.secondary};    
  }
  &::-webkit-scrollbar {
    width: 0.5em;
    height: 0.5em;
    background: none;
    scroll-behavior: smooth;
  }
  &::-webkit-scrollbar-thumb {
    opacity: 0.5;
    border-radius: 15px;
    background: ${COLOR_SCHEME.gray};
  }
  &::-webkit-scrollbar-track {
    background: none;
  }
`;
