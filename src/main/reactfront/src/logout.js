import { useNavigate } from "react-router-dom";
import { useLogin } from "./AuthContext";

const Logout = () => {
    const navigate = useNavigate();
    const { setIsLoggedIn, setLoginUser } = useLogin();
    const fetchLogout = async () => {
        try {

            console.log("Starting logout process");
            // 로그아웃 요청 시 백엔드에서 refresh token 블랙리스트 처리 (혹은 refresh 토큰 DB 에서 삭제)


            window.localStorage.removeItem("Authorization");
            window.localStorage.removeItem("name");
            window.localStorage.removeItem("username");

            setIsLoggedIn(false);
            setLoginUser(null);

            const response = await fetch("http://localhost:8080/logout", {
                method: "POST",
                credentials: "include",
            });

            if (response.ok) {
                alert("logout successful");
                // access token 삭제 (로컬 스토리지)

            } else {
                alert("logout failed");
            }
            navigate("/", { replace: true });
        } catch (error) {
            console.log("error: ", error);
        }
    }
    fetchLogout();
    return;
}

export default Logout;