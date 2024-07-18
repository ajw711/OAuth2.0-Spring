import React from 'react';
import { Link } from 'react-router-dom';

const ViewAccount = () => {
    return (
        <div>
            <h2>계좌보기</h2>
            <button>
                <Link to="/summary">계좌요약</Link>
            </button>
            <button>
                <Link to="/detail">계좌상세</Link>
            </button>
            <button>
                <Link to="/analysis">계좌분석</Link>
            </button>
        </div>
    );
};

export default ViewAccount;