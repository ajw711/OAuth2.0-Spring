import { Link } from 'react-router-dom';
import { useLogin } from './AuthContext';
import './NavBar.css';

const NavBar = () => {
    const { isLoggedIn, isAdmin } = useLogin();
    return (
        <div>
            <div className="navbar">
                <nav>
                    <Link className="navbarMenu" to="/">Home</Link>
                    {!isLoggedIn && <Link className="navbarMenu" to="/login">Login</Link>}
                    {isLoggedIn && isAdmin && <Link className="navbarMenu" to="/admin">관리자</Link>}
                    {isLoggedIn && <Link className="navbarMenu" to="/createAccount">계좌생성</Link>}
                    {isLoggedIn && <Link className="navbarMenu" to="/view">계좌보기</Link>}
                    {isLoggedIn && <Link className="navbarMenu" to="/logout">Logout</Link>}
                </nav>
            </div>
        </div>
    );
}

export default NavBar;