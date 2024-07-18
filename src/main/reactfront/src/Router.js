import { Routes, Route } from 'react-router-dom';
import LoginForm from './login';
import Home from './home';
import { useLogin } from './AuthContext';
import Oauth2Redirect from './Oauth2Redirect';
import Logout from "./logout";
import Account from "./account";
import CreateAccount from  "./createAccount";
import GeneralAccount from  "./generalAccount";
import DepositAccount from "./depositAccount";
import AccountSummary from "./summary";
import AccountDetail from "./detail";
import Transaction from "./transaction";
import DepositTransaction from "./depositTransaction";
import Remittance from "./remittance";
import Analysis from "./analysis";
import Admin from "./admin";
import ViewAccount from "./view";


const MyRoutes = () => {
    const { isLoggedIn } = useLogin();
    // 로그인 여부에 따라서 조건부 라우팅
    return (
        <Routes>
            <Route path="/" element={<Home />} />
            {!isLoggedIn && <Route path="/login" element={<LoginForm />} />}
            {isLoggedIn && <Route path="/logout" element={<Logout />} />}
            {isLoggedIn && <Route path="/account" element={<Account/>} />}
            {isLoggedIn && <Route path="/createAccount" element={<CreateAccount />} />}
            {isLoggedIn && <Route path="/generalAccount" element={<GeneralAccount />} />}
            {isLoggedIn && <Route path="/depositAccount" element={<DepositAccount />} />}
            {isLoggedIn && <Route path="/summary" element={<AccountSummary />} />}
            {isLoggedIn && <Route path="/detail" element={<AccountDetail />} />}
            {isLoggedIn && <Route path="/transaction" element={<Transaction />} />}
            {isLoggedIn && <Route path="/depositTransaction" element={<DepositTransaction />} />}
            {isLoggedIn && <Route path="/remittance" element={<Remittance />} />}
            {isLoggedIn && <Route path="/analysis" element={<Analysis />} />}
            {isLoggedIn && <Route path="/view" element={<ViewAccount />} />}
            <Route path="/admin" element={<Admin />} />
            <Route path="/oauth2-jwt" element={<Oauth2Redirect />} />
        </Routes>
    );
}

export default MyRoutes;