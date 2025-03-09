'use client';
import {useEffect, useState} from "react";

export default function login() {
    var [username, setUsername] = useState("");
    var [password, setPassword] = useState("");
    var [rPassword, setRPassword] = useState("");
    var [name, setName] = useState("");

    var signinSubmit = async () => {
        if (username == "" || password == "" || name == "") {
            alert("정보를 제대로 입력해주세요")
        } else if (password != rPassword) {
            alert("비밀번호가 틀렸습니다")
        } else {
            try {
                var response = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/signin/signin`, {method: "POST", headers: {"Content-Type": "application/json"}, body: JSON.stringify({username: username, password: password, name: name})});
                var data = await response.json();
                console.log(data)
                if (data.message == "이미 존재하는 아이디 입니다") {
                    alert(data.message);
                } else {
                    alert("회원가입이 완료되었습니다")
                    location.href = "/login";
                }
            } catch (error) {
                console.log(error)
            }
        }
    }

    return (
        <div>
            <h1>회원가입</h1>
            <div className="signin-input-box">
                <input type="text" placeholder="아이디 입력" name="username" onChange={(e) => setUsername(e.target.value.trim())} required></input>
            </div>
            <div className="signin-input-box">
                <input type="text" placeholder="이름 입력" name="name" onChange={(e) => setName(e.target.value.trim())} required></input>
            </div>
            <div className="signin-input-box">
                <input type="password" placeholder="비밀번호 입력" name="password" onChange={(e) => setPassword(e.target.value.trim())} autoComplete="off" required></input>
                <input type="password" placeholder="비밀번호 다시 입력" name="password" onChange={(e) => setRPassword(e.target.value.trim())} autoComplete="off" required></input>
            </div>
            <button className="signin-btn-submit" type="submit" onClick={signinSubmit}>로그인</button>
        </div>
    )
}