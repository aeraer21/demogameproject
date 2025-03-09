function generateCode() {
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
    let code = '';
    
    for (let i = 0; i < 6; i++) {
        const randomIndex = Math.floor(Math.random() * characters.length);
        code += characters[randomIndex];
    }
    
    return code;
}
class CreateRoom extends Phaser.Scene {
    constructor() {
        super({key: "createroom"});
    }
    preload() {
        this.roomCode = generateCode();
    }
    create() {
        var roomCreateGui = document.getElementById("box");
        var roomCodeElement = document.getElementById("room-code");
        roomCodeElement.innerText = "방코드: " + String(this.roomCode);
        roomCreateGui.style.display = "block";
        ws.onmessage = e => {
            var data = JSON.parse(e.data);
        }
    }
    update() {

    }
}
window.CreateRoom = CreateRoom;