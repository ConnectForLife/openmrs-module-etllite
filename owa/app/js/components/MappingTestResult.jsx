import ReactTable from 'react-table';
import 'react-table/react-table.css'
import TextLabel from './TextLabel';
import * as Default from '../utils/messages';
import { getIntl } from "@openmrs/react-components/lib/components/localization/withLocalization";
import ErrorDesc from './ErrorDesc';
import PropTypes from 'prop-types';

const getColumns = (data) => {
  return Object.keys(data[0]).map((k) => {
    return {
      Header: k,
      accessor: k
    }
  });
}

const isDataCorrect = (data) => {
  try {
    getColumns(data);
    return true;
  } catch(ex) {
    return false;
  }
}

const renderResults = (data, label) => {
  if (isDataCorrect(data)) {
    return (
      <div className="result-table">
        <TextLabel text={label}
          isWithColon={true} />
        <ReactTable data={data}
          columns={getColumns(data)}
          defaultPageSize={5}
          showPageJump={false} />
      </div>
    );
  } else {
    return (
      <div className="result-table">
        <ErrorDesc field={[label, getIntl().formatMessage({ id: 'ETL_MAPPING_TEST_PARSE_FAILURE', defaultMessage: Default.MAPPING_TEST_PARSE_FAILURE })].join(': ')} />
      </div>
    );
  }
}

const MappingTestResult = (props) => {
  const { data } = props;
  if (!!data) {
    return (
      <div>
        {renderResults(data.extracted, getIntl().formatMessage({ id: 'ETL_MAPPING_TEST_RESULTS_EXTRACTED_LABEL', defaultMessage: Default.MAPPING_TEST_RESULTS_EXTRACTED_LABEL }))}
        {renderResults(data.transformed, getIntl().formatMessage({ id: 'ETL_MAPPING_TEST_RESULTS_TRANSFORMED_LABEL', defaultMessage: Default.MAPPING_TEST_RESULTS_TRANSFORMED_LABEL }))}
      </div>
    );
  } else return null;
}

export default MappingTestResult;

MappingTestResult.propTypes = {
  data: PropTypes.shape({
    extracted: PropTypes.any.isRequired,
    transformed: PropTypes.any.isRequired
  }),
};
