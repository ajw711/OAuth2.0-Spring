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
            setError('Failed to fetch accounts');
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
                        <p>Account Type: {account.accountType}</p>
                        <p>Account Number : {account.accountNumber}</p>
                        <p>Balance: {account.balance}</p>
                        <p>Name: {account.customerName}</p>
                        <p>Create Date: {account.createDate}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default AccountDetail;