import {createContext, useContext, useEffect, useState} from "react";

const AuthContext=createContext();

const AuthProvider = ({ children }) => {
    // 기본적으로 로컬 스토리지의 access 값을 기준으로 setting
    const [isLoggedIn, setIsLoggedIn] = useState(!!window.localStorage.getItem('Authorization'));
    const [loginUser, setLoginUser] = useState(window.localStorage.getItem('name'));
    const [isAdmin, setIsAdmin]=useState(window.localStorage.getItem('name') ==='ROLE');




    return (
        <AuthContext.Provider value={{ isLoggedIn, setIsLoggedIn, loginUser, setLoginUser, isAdmin}}>
            {children}
        </AuthContext.Provider>
    );
};

export const useLogin = () => useContext(AuthContext);
export default AuthProvider;