import { useNavigate, useSearchParams } from "react-router-dom";
import { useLogin } from "./AuthContext";

const OAuth2Redirect = () => {
    const navigate = useNavigate();
    const { setIsLoggedIn, setLoginUser } = useLogin();

    const OAuth2JwtHeaderFetch = async () => {
        const [queryParams] = useSearchParams();
        try {
            const response = await fetch("http://localhost:8080/oauth2-jwt", {
                method: "POST",
                credentials: "include",
            });

            if (response.ok) {
                // local storage access token set
                const accessToken = response.headers.get("Authorization");
                console.log("Access Token:", accessToken);
                window.localStorage.setItem("Authorization", response.headers.get("Authorization"));
                // local storage name set
                const name = queryParams.get('name');
                window.localStorage.setItem("name", name);


                setIsLoggedIn(true);
                setLoginUser(name);
            } else {
                alert('접근할 수 없는 페이지입니다.');
            }
            navigate('/', { replace: true });
        } catch (error) {
            console.log("error: ", error);
        }
    }

    // request access token in header using httpOnly cookie, and set access token to local storage
    OAuth2JwtHeaderFetch();
    return;
};


export default OAuth2Redirect;