import React from 'react';

const ErrorDesc = (props) => {
  const { field } = props;

  return (!!field && (
    <div className="error-description">
      {field}
    </div>
  ));
};

export default ErrorDesc;
