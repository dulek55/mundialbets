import React from 'react';
import  ReactCollapsible from 'react-collapsible';

const Collapsible = ({ trigger, children }) => {
  return (
    <ReactCollapsible trigger={trigger}>
      {children}
    </ReactCollapsible>
  );
};

export default Collapsible;