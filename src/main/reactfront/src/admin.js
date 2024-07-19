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
                                setMessage('접근권한이 없는 페이지.');
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
                        console.error('유저 에러:', error);
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
                        console.error('계좌 에러 :', error);
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
                        console.error('잘못된 유저 삭제:', error);
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
                        console.error('잘못된 계좌 삭제:', error);
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
                                                    <span>회원이름 : {user.name}</span>
                                                    <span> </span>
                                                    <span>회원 아이디 : {user.customerName}</span>
                                                    <span> </span>
                                                    <span>회원 이메일 : {user.email}</span>
                                                    <span> </span>
                                                    <span>회원가입일자 : {user.createDate}</span>
                                                    <span> </span>
                                                    <span>회원업데이트일자 : {user.updateDate}</span>
                                                    <span> </span>
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
                                                    <span>회원이름 : {account.customerName}</span>
                                                    <span> </span>
                                                    <span>회원 계좌번호 : {account.accountNumber}</span>
                                                    <span> </span>
                                                    <span>계좌액 : {account.balance}</span>
                                                    <span> </span>
                                                    <span>금리 : {account.interestRate}</span>
                                                    <span> </span>
                                                    <span>기간 : {account.termMonth}</span>
                                                    <span> </span>
                                                    <span>가입일자 : {account.createDate}</span>
                                                    <span> </span>
                                                    <span>업데이트일자 : {account.updateDate}</span>
                                                    <span> </span>
                                                    <span>계좌 종류 : {account.accountType}</span>
                                                    <span> </span>
                                                    <button onClick={() => deleteAccount(account.accountNumber)}>삭제
                                                    </button>
                                            </li>
                                        ))}
                                </ul>
                        </div>
                    )}
            </div>
        );
};

export default Admin;