class FindRoom extends Phaser.Scene {
    constructor() {
        super({key: "findroom"});
    }
    preload() {
        this.roomList = [];
    }
    create() {
        ws.send(JSON.stringify({type: "findallrooms"}));
        var rooms = this.add.group();
        ws.onmessage = e => {
            var data = JSON.parse(e.data);
            for (var i = 0; i < data.length; i++) {
                var room = this.add.graphics();
                room.setInteractive(new Phaser.Geom.Rectangle(window.innerWidth / 2 - 300, 100 * i, 600, 100), Phaser.Geom.Rectangle.Contains);
                room.lineStyle(2, "#000000");
                room.strokeRect(window.innerWidth / 2 - 300, 100 * i, 600, 100);
                room.fillStyle(0xffffff);
                room.fillRect(window.innerWidth / 2 - 300, 100 * i, 600, 100);
                var name = this.add.text(window.innerWidth / 2 - 280, 10 + 100 * i, data[i].roomname, {fontSize: 40, fontFamily: 'Arial', fill: "#000000", align: "left"}).setOrigin(0, 0).setInteractive();
                var playerNum = this.add.text(window.innerWidth / 2 + 100, 50 + 100 * i, data[i].playerlist.length, {fontSize: 30, fontFamily: 'Arial', fill: "#000000", align: "left"}).setOrigin(0, 0).setInteractive();
                room.setName(data[i].roomid);
                name.setName(data[i].roomid);
                playerNum.setName(data[i].roomid);
                rooms.add(room);
                rooms.add(name);
                rooms.add(playerNum);
            }
        }
        this.input.on('gameobjectover', (pointer, gameObject) => {
            if (rooms.contains(gameObject)) {
                canvas.style.cursor = "pointer";
            }
        });
        this.input.on('gameobjectout', (pointer, gameObject) => {
            if (rooms.contains(gameObject)) {
                canvas.style.cursor = "default";
            }
        });
        this.input.on('gameobjectdown', (pointer, gameObject) => {
            if (rooms.contains(gameObject)) {
                console.log(gameObject.name)
                ws.send(JSON.stringify({type: "enterroom", roomid: gameObject.name}));
            }
        });
    }
    update() {

    }
}
window.FindRoom = FindRoom;