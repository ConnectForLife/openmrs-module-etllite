import React from 'react';
import { ControlLabel } from 'react-bootstrap';
import * as Default from '../utils/messages'
import { getIntl } from "@openmrs/react-components/lib/components/localization/withLocalization";

const renderMandatoryField = () => {
  return (
    <i> ({getIntl().formatMessage({ id: 'ETL_REQUIRED_LABEL', defaultMessage: Default.REQUIRED_LABEL })}) </i>
  )
}

const renderColon = () => {
  return (
    <span>:</span>
  )
}

const TextLabel = (props) => {
  const { text, isMandatory, isWithColon } = props;

  return (!!text && (
    <ControlLabel>
      {text}
      {isMandatory && renderMandatoryField()}
      {isWithColon && renderColon()}
    </ControlLabel>
  ));
};

export default TextLabel;
