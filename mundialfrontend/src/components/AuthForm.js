import React from 'react';

const AuthForm = ({ title, onSubmit, children }) => {
  return (
    <div>
      <h2>{title}</h2>
      <form onSubmit={onSubmit}>
        {children}
        <div>
          <button type="submit">Submit</button>
        </div>
      </form>
    </div>
  );
};

export default AuthForm;