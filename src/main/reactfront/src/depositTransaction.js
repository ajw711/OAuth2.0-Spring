import React, {useState } from 'react';
import axios from 'axios';
import {useNavigate} from "react-router-dom";

const DepositTransaction = () => {
    const [amount, setAmount] = useState(0);
    //const [token, setToken] = useState('');
    const [accountNumber, setAccountNumber] = useState(0);
    const navigate = useNavigate();



    const handleAccountNumberChange = (e) => {
        setAccountNumber(e.target.value);
    };


    const handleAmountChange = (e) => {
        setAmount(e.target.value);
    };


    const handleSubmit = async (event) => {
        event.preventDefault();

        const data = {
            amount: amount,
            accountNumber: accountNumber

        };
        try {
            const response = await axios.post("http://localhost:8080/account/transaction/deposit", data, {
                headers: {
                    'Content-Type': 'application/json',
                    //'Authorization': `${token}`
                }
            });
            if(response.status === 200){
                navigate("/view");
            alert('Success');
            }
            // 성공 시 필요한 작업 수행
        } catch (error) {
            alert('계좌번호가 다릅니다');
            console.error('Error:', error);
            // 오류 처리
        }
    };

    return (
        <div>
            <h2>Create Deposit Account</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    계좌번호:
                    <input
                        type="number"
                        value={accountNumber}
                        onChange={handleAccountNumberChange}
                    />
                </label>
                <br/>
                <br/>
                <label>
                    금액:
                    <input
                        type="number"
                        value={amount}
                        onChange={handleAmountChange}
                    />
                </label>
                <br/>
                <br/>
                <button type="submit">Submit</button>
            </form>
        </div>
    );
};

export default DepositTransaction;