'use client';

import Link from "next/link";
import { useEffect, useState } from "react";
import indexCss from "./page.module.css"
import Head from "next/head";
import Script from "next/script";
import CreateRoom from "./createGui";
import "./globals.css";

export default function Home() {
    var [loginState, setLoginState] = useState("");
    var [ws, setWs] = useState(null);
    useEffect(() => {
        if (localStorage.getItem("username") != null) {
            setLoginState(localStorage.getItem("username"));
        } else {
            setLoginState("");
        }
    }, []);
    return(
        <div>
            {
                loginState != "" ? (
                    <div>
                        <h1>{loginState}</h1>
                        <Link href="/logout">
                            <button>로그아웃</button>
                        </Link>
                        <canvas id={indexCss.canvas}></canvas>
                        <CreateRoom></CreateRoom>
                        <Script src="https://cdn.jsdelivr.net/npm/phaser@latest/dist/phaser.min.js" strategy="afterInteractive" ></Script>
                        <script>
                            window.Phaser = Phaser;
                        </script>
                        <Script src="/js/scenes/createroom.js"></Script>
                        <Script src="/js/scenes/findroom.js"></Script>
                        <Script src="/js/scenes/title.js"></Script>
                        <Script src="/js/game.js" strategy="afterInteractive" />
                    </div>
                ) : (
                    <div>
                        <Link href="/login">
                            <button>로그인</button>
                        </Link>
                        <Link href="/signin">
                            <button>회원가입</button>
                        </Link>
                    </div>
                )
            }
        </div>
    )
}