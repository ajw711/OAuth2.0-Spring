import React, { useEffect, useState } from 'react';
import axios from 'axios';

const AccountDetail = () => {
    const [accounts, setAccounts] = useState([]);
    const [token, setToken] = useState('');
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    // 토큰을 localStorage에서 가져오거나 세팅하는 부분
    useEffect(() => {
        const storedToken = window.localStorage.getItem('Authorization');
        if (storedToken) {
            setToken(storedToken);
        }
    }, []);

    useEffect(() => {
        if (token) {
            fetchAccounts();
        }
    }, [token]);

    const fetchAccounts = async () => {
        try {
            const response = await axios.get("http://localhost:8080/account/view/detail", {
                headers: {
                    'Authorization': `${token}`
                }
            });
            setAccounts(response.data);
        } catch (error) {
            setError('잘못된 계좌');
        } finally {
            setLoading(false);
        }
    };

    return (
        <div>
            <h2>All Accounts</h2>
            {loading && <p>Loading...</p>}
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <ul>
                {accounts.map((account) => (
                    <li key={account.id}>
                        <p>계좌 종류: {account.accountType}</p>
                        <p>계좌 번호 : {account.accountNumber}</p>
                        <p>츌금가능금액 : {account.balance}</p>
                        <p>이름 : {account.customerName}</p>
                        <p>개설일 : {account.createDate}</p>


                        <br></br>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default AccountDetail;