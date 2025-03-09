class Title extends window.Phaser.Scene {
    constructor() {
        super({key: "title"});
    }
    preload() {

    }
    create() {
        var btns = [
            this.add.text(window.innerWidth / 2, window.innerHeight / 2, "방찾기", {fontSize: 70, fontFamily: 'Arial', fill: "#000000", align: "center"}),
            this.add.text(window.innerWidth / 2, window.innerHeight / 2 + 100, "방만들기", {fontSize: 70, fontFamily: 'Arial', fill: "#000000", align: "center"})
        ];
        btns.forEach(e => {
            e.setOrigin(0.5, 0.5);
            e.setInteractive();
            e.on("pointerover", () => {
                canvas.style.cursor = "pointer";
            });
            e.on("pointerout", () => {
                canvas.style.cursor = "default";
            });
            e.on("pointerdown", () => {
                this.input.removeAllListeners();
                this.scene.start(e.text == "방찾기" ? "findroom" : "createroom");
            });
        });
    }
    update() {

    }
}
window.Title = Title;