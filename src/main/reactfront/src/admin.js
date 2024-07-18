import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Admin = () => {
        const [message, setMessage] = useState('');
        const [users, setUsers] = useState([]);
        const [accounts, setAccounts] = useState([]);
        const [showUsers, setShowUsers] = useState(false);
        const [showAccounts, setShowAccounts] = useState(false);
        const [token, setToken] = useState('');


        // 토큰을 localStorage에서 가져오거나 세팅하는 부분
        useEffect(() => {
                const storedToken = window.localStorage.getItem('Authorization');
                if (storedToken) {
                        setToken(storedToken);
                }
        }, []);

        useEffect(() => {
                const fetchAdminPage = async () => {
                        try {
                                const response = await axios.get('http://localhost:8080/admin', {
                                        headers: {
                                                'Authorization': `${token}`
                                        }
                                });
                                setMessage(response.data);
                        } catch (error) {
                                setMessage('You do not have access to this page.');
                        }
                };
                if (token) {
                        fetchAdminPage();
                }

        }, []);

        const fetchUsers = async () => {
                try {
                        const response = await axios.get('http://localhost:8080/admin/allList', {
                                headers: {
                                        'Authorization': `${token}`
                                }
                        });
                        setUsers(response.data);
                        setShowUsers(true);
                        setShowAccounts(false);
                } catch (error) {
                        console.error('Failed to fetch users:', error);
                }
        };

        const fetchAccounts = async () => {
                try {
                        const response = await axios.get('http://localhost:8080/admin/allAccount', {
                                headers: {
                                        'Authorization': `${token}`
                                }
                        });
                        setAccounts(response.data);
                        setShowUsers(false);
                        setShowAccounts(true);
                } catch (error) {
                        console.error('Failed to fetch accounts:', error);
                }
        };

        const deleteUser = async (customerName) => {
                try {
                        await axios.delete(`http://localhost:8080/admin/delete/user/${customerName}`, {
                                headers: {
                                        'Authorization': `${token}`
                                }
                        });
                        fetchUsers();
                } catch (error) {
                        console.error('Failed to delete user:', error);
                }
        };

        const deleteAccount = async (accountNumber) => {
                try {
                        await axios.delete(`http://localhost:8080/admin/delete/account/${accountNumber}`, {
                                headers: {
                                        'Authorization': `${token}`
                                }
                        });
                        fetchAccounts();
                } catch (error) {
                        console.error('Failed to delete account:', error);
                }
        };

        return (
            <div>
                    <h2>관리자 페이지</h2>
                    <nav>
                            <button onClick={fetchUsers}>회원 리스트 보기</button>
                            <button onClick={fetchAccounts}>계좌 리스트 보기</button>
                    </nav>
                    <p>{message}</p>
                    {showUsers && (
                        <div>
                                <h3>회원 리스트</h3>
                                <ul>
                                        {users.map(user => (
                                            <li key={user.id}>
                                                    <span>{user.name}</span>
                                                    <span>{user.customerName}</span>
                                                    <span>{user.email}</span>
                                                    <span>{user.createDate}</span>
                                                    <span>{user.updateDate}</span>
                                                    <button onClick={() => deleteUser(user.customerName)}>삭제</button>
                                            </li>
                                        ))}
                                </ul>
                        </div>
                    )}
                    {showAccounts && (
                        <div>
                                <h3>계좌 리스트</h3>
                                <ul>
                                        {accounts.map(account => (
                                            <li key={account.id}>
                                                    <span>{account.customerName}</span>
                                                    <span>{account.accountNumber}</span>
                                                    <span>{account.balance}</span>
                                                    <span>{account.interestRate}</span>
                                                    <span>{account.termMonth}</span>
                                                    <span>{account.createDate}</span>
                                                    <span>{account.updateDate}</span>
                                                    <span>{account.accountType}</span>
                                                    <button onClick={() => deleteAccount(account.accountNumber)}>삭제</button>
                                            </li>
                                        ))}
                                </ul>
                        </div>
                    )}
            </div>
        );
};

export default Admin;