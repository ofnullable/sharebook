import React from 'react';
import Link from 'next/link';

import { SubMenuWrapper } from './styled';

/**
 * menus = {
 *   [menuname]: ${menuName},
 * }
 *
 * <SubMenu href='/path/with/[menu]' currentMenu={menu} menus={menus} />
 */

const SubMenu = ({ href, currentMenu, menus }) => {
  const base = href.split('[')[0];
  return (
    <SubMenuWrapper>
      {Object.keys(menus).map(menu => (
        <Link href={href} as={`${base}${menu}`} key={menu}>
          <a>
            <span className={currentMenu === menu ? 'active' : ''}>{menus[menu]}</span>
          </a>
        </Link>
      ))}
    </SubMenuWrapper>
  );
};

export default SubMenu;
