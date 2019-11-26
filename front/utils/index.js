import { useState, useCallback } from 'react';
import gravatar from 'gravatar';

import { IMAGE_BASE_URL } from './consts';

export const useInput = (initialValue = '') => {
  const [value, setter] = useState(initialValue);
  const handler = useCallback(e => {
    setter(e.target.value);
  }, []);
  return [value, handler, setter];
};

export const isBlank = (...values) => {
  return values.some(v => !!v.trim() === false);
};

export const hasWhitespace = value => /\s/.test(value);

export const isEmail = value => {
  return /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,6}$/.test(value);
};

export const preventDefaultEvent = e => {
  e.preventDefault();
};

export const getAvatar = uri => {
  if (!uri) return;
  return uri.startsWith('http') ? uri : `${IMAGE_BASE_URL}${uri}`;
};

export const getGravatar = (email, size) =>
  gravatar.url(email, { protocol: 'https', s: size, d: 'identicon' });
