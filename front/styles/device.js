/* 
  ##Device = Desktops
  ##Screen = 1281px to higher resolution desktops
*/
const desktops = styles => `
  @media (min-width: 1281px) {
    ${styles}
  }
`;

/* 
##Device = Laptops, Desktops
##Screen = B/w 1025px to 1280px
*/
const laptops = styles => `
  @media (min-width: 1025px) and (max-width: 1280px) {
    ${styles}
  }
`;

/* 
##Device = Tablets, Ipads (portrait)
##Screen = B/w 768px to 1024px
*/
const tablets = styles => `
  @media (min-width: 768px) and (max-width: 1024px) {
    ${styles}
  }
`;

/* 
##Device = Tablets, Ipads (landscape)
##Screen = B/w 768px to 1024px
*/
const landTablets = styles => `
  @media (min-width: 768px) and (max-width: 1024px) and (orientation: landscape) {
    ${styles}
  }
`;

/* 
##Device = Most of the Smartphones Mobiles (Portrait)
##Screen = B/w 320px to 479px
*/
const mobiles = styles => `
  @media (min-width: 320px) and (max-width: 767px) {
    ${styles}
  }
`;

export default {
  desktops,
  laptops,
  tablets,
  landTablets,
  mobiles,
};
