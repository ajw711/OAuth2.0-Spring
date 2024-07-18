import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Analysis = () => {
    const [analysisData, setAnalysisData] = useState({});
    const [token, setToken] = useState('');
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [filePath, setFilePath] = useState('');

    useEffect(() => {
        const storedToken = window.localStorage.getItem('Authorization');
        if (storedToken) {
            setToken(storedToken);
        }
    }, []);

    useEffect(() => {
        if (token) {
            fetchAnalysis();
        }
    }, [token]);

    const fetchAnalysis = async () => {
        try {
            const response = await axios.get("http://localhost:8080/account/analysis", {
                headers: {
                    'Authorization': `${token}`
                }
            });
            setAnalysisData(response.data);
            setFilePath(response.data.filePath); // filePath 설정
        } catch (error) {
            setError('Failed to fetch analysis data');
        } finally {
            setLoading(false);
        }
    };

    const downloadFile = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/account/analysis/download/`, {
                headers: {
                    'Authorization': `${token}`
                },
                responseType: 'blob'
            });

            const url = window.URL.createObjectURL(new Blob([response.data]));
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', filePath.split('\\').pop());
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
        } catch (error) {
            setError('Failed to download file');
        }
    };





    return (
        <div>
            <h2>Analysis Data</h2>
            {loading && <p>Loading...</p>}
            {error && <p style={{ color: 'red' }}>{error}</p>}
            {!loading && !error && (
                <div>
                    <ul>
                        <li>입금 횟수: {analysisData.depositCount}</li>
                        <li>계좌이체 횟수: {analysisData.transferCount}</li>
                        <li>총입금액: {analysisData.totalDeposit}</li>
                        <li>총이체금액: {analysisData.totalTransfer}</li>
                        <li>최대 입금액: {analysisData.maxDeposit}</li>
                        <li>최소 입금액: {analysisData.minDeposit}</li>
                        <li>최대 이체금액: {analysisData.maxTransfer}</li>
                        <li>최소 이체금액: {analysisData.minTransfer}</li>
                        <li>평균 입금액: {analysisData.avgDeposit}</li>
                        <li>평균 이체금액: {analysisData.avgTransfer}</li>
                    </ul>
                    <button onClick={downloadFile}>Download Analysis File</button>
                </div>
            )}
        </div>
    );
};

export default Analysis;