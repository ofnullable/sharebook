import { useState, useCallback } from 'react';

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
