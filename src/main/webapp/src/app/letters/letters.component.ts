import {Component} from "@angular/core";

@Component({
    selector: "letters",
    templateUrl: "letters.component.html",
    styleUrls: ["letters.component.css"]
})
export class LettersComponent {
    showLetterCompose = false;

    constructor() {
    }

    toggleCompose() {
        this.showLetterCompose = !this.showLetterCompose;
    }
}