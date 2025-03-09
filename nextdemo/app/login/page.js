'use client';
import {useEffect, useState} from "react";

export default function login() {
    var [username, setUsername] = useState("");
    var [password, setPassword] = useState("");

    var loginSubmit = async () => {
        try {
            var response = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/signin/check`, {method: "POST", headers: {"Content-Type": "application/json"}, body: JSON.stringify({username: username, password: password})});
            var data = await response.json();
            if (data.message.slice(0, 5) == "환영합니다") {
                alert(data.message);
                console.log(data.message)
                localStorage.setItem("username", data.message.slice(6, -1));
                location.href = "/"
            } else {
                alert(data.message);
            }
        } catch (error) {
            console.log(error)
        }
    }

    return (
        <div>
            <h1>로그인</h1>
            <div className="signin-input-box">
                <input type="text" placeholder="아이디 입력" name="username" onChange={(e) => setUsername(e.target.value)} required></input>
            </div>
            <div className="signin-input-box">
                <input type="password" placeholder="비밀번호 입력" name="password" onChange={(e) => setPassword(e.target.value)} autoComplete="off" required></input>
            </div>
            <button className="signin-btn-submit" type="submit" onClick={loginSubmit}>로그인</button>
        </div>
    )
}