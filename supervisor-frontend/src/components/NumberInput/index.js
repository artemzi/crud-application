import React  from 'react';
import PropTypes from 'prop-types';

const NumberInput = ({handleChange, title, value}) => (
  <div>
    <br/>
    {title}
    <br/>
    <input type="text" value={value} onChange={(event) => handleChange(event.target.value)} />
    <br/>
  </div>
);


NumberInput.propTypes = {
  handleChange: PropTypes.func,
  title: PropTypes.string,
  value: PropTypes.number,
};

NumberInput.defaultProps = {
  handleChange: (event) => console.info(`New value : ${event.target.value}`),
  title: null,
  value: null,
};

export default NumberInput;
