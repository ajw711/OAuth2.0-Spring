import React from 'react';
import { Link } from 'react-router-dom';

const Account = () => {
    return (
        <div>
            <h1>Account Management</h1>
            <button>
                <Link to="/createAccount">Create Account</Link>
            </button>
        </div>
    );
};

export default Account;