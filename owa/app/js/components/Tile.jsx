import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import _ from 'lodash';

const Tile = (props) => {
  const { name, href, icon, simpleIcon } = props;
  const simpleIconComponent = <i className={icon + ' big'} />;
  const defaultIconComponent = _.isEmpty(icon) ?
    <i className='icon-align-justify big' /> :
    <FontAwesomeIcon size='3x' icon={icon} />;
  
  return (
    <a href={href} className="button app big" >
      {
        simpleIcon ? simpleIconComponent : defaultIconComponent
      }
      <h5>{name}</h5>
    </a>
  );
}

Tile.defaultProps = {
  name: 'New App',
  href: '#/',
  icon: [],
  simpleIcon: false
}

export default Tile;