import { Link } from 'react-router-dom';
import './../style/Login.css'
import { useEffect, useState } from 'react';

export default function HomePage() {
    const [userId, setUserId] = useState<String|null>("");
    
    useEffect(() =>{
        const stordeUserId = localStorage.getItem("userId");

        if(stordeUserId !== null){
            setUserId(stordeUserId);
        }

    }, []);

    return (
        <>
            <p>Hello user: {userId}</p>

            <span className="psw"><Link to={'/login'} >Log In</Link></span>
            <span className="psw"><Link to={'/create'} >Sign in</Link></span>
        </>
    );
}