import React from 'react';
import { Link } from 'react-router-dom';

const CreateAccount = () => {
    return (
        <div>
            <h2>계좌생성</h2>
            <button>
                <Link to="/generalAccount">일반계좌</Link>
            </button>
            <button>
                <Link to="/depositAccount">예금계좌</Link>
            </button>
        </div>
    );
};

export default CreateAccount;