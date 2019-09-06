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
  @media (min-width: 1025px) {
    ${styles}
  }
`;

/* 
##Device = Tablets (include low resolution Tablets), Ipads (Portrait)
##Screen = B/w 768px to 1024px
*/
const tablets = styles => `
  @media (min-width: 768px) and (max-width: 1024px) {
    ${styles}
  }
`;

/* 
##Device = Most of the Smartphones Mobiles (Portrait), Mobiles (Landscape)
##Screen = B/w 320px to 767px
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
  mobiles,
};
