import React, {useEffect, useState} from 'react';
import axios from 'axios';

const GeneralAccount = () => {
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [balance, setBalance] = useState(0);
    const [passwordMatch, setPasswordMatch] = useState(true);
    const [token, setToken] = useState('');


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

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (!passwordMatch) {
            alert('Passwords do not match!');
            return;
        }
        const data = {
            accountType: "GENERAL",
            password: password,
            balance: balance
        };
        try {
            const response = await axios.post("http://localhost:8080/account/create/general", data, {
                headers: {
                    'Content-Type': 'application/json',
                    // 'Authorization': window.localStorage.getItem('Authorization') // 인증 토큰 추가
                    'Authorization': `${token}`
                }
            });
            alert('Success: Password saved');
            // 성공 시 필요한 작업 수행
        } catch (error) {
            console.error('Error:', error);
            // 오류 처리
        }
    };

    return (
        <div>
            <h2>Create General Account</h2>
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
                <button type="submit">Submit</button>
            </form>
        </div>
    );
};

export default GeneralAccount;