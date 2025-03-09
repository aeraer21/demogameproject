const ws = new WebSocket("ws://125.183.95.158:8080/ws");
var players = [];
var message = {};
var size = {w: window.innerWidth, h: innerHeight};
var gravity = 300;
const canvas = document.getElementsByTagName("canvas")[0];
const config = {
    type: window.Phaser.CANVAS,
    width: size.w,
    height: size.h,
    canvas: canvas,
    backgroundColor: '#ffffff', 
    physics: {
        default: "arcade",
        arcade: {
            y: gravity
        }
    },
    scene: [window.Title, window.FindRoom, window.CreateRoom]
}
ws.onopen = e => {
    ws.send(JSON.stringify({"id": localStorage.getItem("username"), "type": "join"}));
}
var game = new window.Phaser.Game(config);
