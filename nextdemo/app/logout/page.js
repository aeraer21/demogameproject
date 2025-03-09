'use client';
import {useEffect, useState} from "react";

export default function login() {
    var [password, setPassword] = useState("");

    var logoutSubmit = async () => {
        try {
            var response = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/signin/logout`, {method: "POST", headers: {"Content-Type": "application/json"}, body: JSON.stringify({username: localStorage.getItem("username"), password: password})});
            var data = await response.json();
            console.log(data)
            if (data) {
                localStorage.removeItem("username");
                alert("로그아웃 되었습니다");
                location.href = "/";
            } else {
                alert("비밀번호가 틀렸습니다");
            }
        } catch (error) {
            console.log(error)
        }
    }

    return (
        <div>
            <input onChange={(e) => {setPassword(e.target.value)}} placeholder="비밀번호 입력" type="password"></input>
            <button onClick={logoutSubmit}>로그아웃</button>
        </div>
    )
}