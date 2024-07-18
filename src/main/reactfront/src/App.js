import './App.css';

import AuthProvider from './AuthContext';
import MyRoutes from './Router';
import NavBar from "./NavBar";

function App() {
    return (
        <div className="App">
            <AuthProvider>
                <NavBar />
                <MyRoutes />
            </AuthProvider>
        </div>
    );
}

export default App;