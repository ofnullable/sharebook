import React, { useState, createContext } from 'react';

export const HamburgerContext = createContext();

export const HamburgerProvider = ({ children }) => {
  const [active, setActive] = useState(false);
  return (
    <HamburgerContext.Provider value={[active, setActive]}>{children}</HamburgerContext.Provider>
  );
};
