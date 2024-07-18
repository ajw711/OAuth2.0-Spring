import React, { useState } from 'react';
import axios from 'axios';

const Remittance = () => {
    const [fromAccountNumber, setFromAccountNumber] = useState('');
    const [toAccountNumber, setToAccountNumber] = useState('');
    const [amount, setAmount] = useState(0);
    const [password, setPassword] = useState('');

    const handleFromAccountNumberChange = (e) => {
        setFromAccountNumber(e.target.value);
    };

    const handleToAccountNumberChange = (e) => {
        setToAccountNumber(e.target.value);
    };

    const handleAmountChange = (e) => {
        setAmount(e.target.value);
    };

    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        const data = {
            fromAccountNumber: fromAccountNumber,
            toAccountNumber: toAccountNumber,
            amount: amount,
            password: password
        };

        try {
            const response = await axios.post("http://localhost:8080/account/transaction/remittance", data, {
                headers: {
                    'Content-Type': 'application/json',
                    // 'Authorization': `${token}`
                }
            });

            if (response.status === 200) {
                alert('Success: Transfer completed');
            } else {
                alert('Transfer failed: ' + response.data);
            }
        } catch (error) {
            if (error.response && error.response.status === 400) {
                alert('Transfer failed: ' + error.response.data);
            } else {
                console.error('Error:', error);
                alert('An unexpected error occurred.');
            }
        }
    };

    return (
        <div>
            <h2>Transfer Money</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    보내는 계좌번호:
                    <input
                        type="text"
                        value={fromAccountNumber}
                        onChange={handleFromAccountNumberChange}
                    />
                </label>
                <br/>
                <br/>
                <label>
                    받는 계좌번호:
                    <input
                        type="text"
                        value={toAccountNumber}
                        onChange={handleToAccountNumberChange}
                    />
                </label>
                <br/>
                <br/>
                <label>
                    Amount:
                    <input
                        type="number"
                        value={amount}
                        onChange={handleAmountChange}
                    />
                </label>
                <br/>
                <br/>
                <label>
                    계좌 비밀번호:
                    <input
                        type="password"
                        value={password}
                        onChange={handlePasswordChange}
                    />
                </label>
                <br/>
                <br/>
                <button type="submit">Submit</button>
            </form>
        </div>
    );
};

export default Remittance;