import React from 'react';
import PropTypes from 'prop-types';

const RemoveButton = (props) => {
  const { handleRemove, tooltip } = props;
  return (
    <i className="medium icon-remove delete-action"
      onClick={handleRemove}
      title={tooltip} />);
};

RemoveButton.defaultProps = {
  tooltip: null
};

RemoveButton.propTypes = {
  handleRemove: PropTypes.func.isRequired,
  tooltip: PropTypes.string
};

export default RemoveButton;
