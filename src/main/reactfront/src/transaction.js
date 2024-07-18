import React from 'react';
import { Link } from 'react-router-dom';

const Transaction = () => {
    return (
        <div>
            <h2>거래</h2>
            <button>
                <Link to="/depositTransaction">입금</Link>
            </button>
            <button>
                <Link to="/remittance">계좌이체</Link>
            </button>
        </div>
    );
};

export default Transaction;