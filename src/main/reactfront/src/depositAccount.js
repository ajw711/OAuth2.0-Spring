import React, { useEffect, useState } from 'react';
import axios from 'axios';
import {useNavigate} from "react-router-dom";

const DepositAccount = () => {
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [balance, setBalance] = useState(0);
    const [passwordMatch, setPasswordMatch] = useState(true);
    const [token, setToken] = useState('');
    const [termMonth, setTermMonth] = useState(0); // 기간 필드
    const interestRate = 3.5; // 이자율 고정값
    const navigate = useNavigate();

    // 토큰을 localStorage에서 가져오거나 세팅하는 부분
    useEffect(() => {
        const storedToken = window.localStorage.getItem('Authorization');
        if (storedToken) {
            setToken(storedToken);
        }
    }, []);

    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
        setPasswordMatch(e.target.value === confirmPassword);
    };

    const handleConfirmPasswordChange = (e) => {
        setConfirmPassword(e.target.value);
        setPasswordMatch(e.target.value === password);
    };

    const handleBalanceChange = (e) => {
        setBalance(e.target.value);
    };

    const handleDurationChange = (e) => {
        setTermMonth(e.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (!passwordMatch) {
            alert('비밀번호가 일치하지 않습니다');
            return;
        }
        const data = {
            accountType: "TERM_DEPOSIT",
            password: password,
            balance: balance,
            termMonth: termMonth, // 기간 필드 추가
            interestRate: interestRate // 이자율 고정값 추가
        };
        try {
            const response = await axios.post("http://localhost:8080/account/create/deposit", data, {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `${token}`
                }
            });
            if(response.ok) {
                navigate("/");
                alert('Success');
            }

        } catch (error) {
            console.error('Error:', error);
            // 오류 처리
        }
    };

    return (
        <div>
            <h2>Create Deposit Account</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Password:
                    <input
                        type="password"
                        value={password}
                        onChange={handlePasswordChange}
                    />
                </label>
                <br />
                <label>
                    Confirm Password:
                    <input
                        type="password"
                        value={confirmPassword}
                        onChange={handleConfirmPasswordChange}
                    />
                </label>
                {!passwordMatch && <p style={{ color: 'red' }}>Passwords do not match!</p>}
                <br />
                <label>
                    Balance:
                    <input
                        type="number"
                        value={balance}
                        onChange={handleBalanceChange}
                    />
                </label>
                <br />
                <label>
                    termMonth (months):
                    <input
                        type="number"
                        value={termMonth}
                        onChange={handleDurationChange}
                    />
                </label>
                <br />
                <button type="submit">Submit</button>
            </form>
        </div>
    );
};

export default DepositAccount;