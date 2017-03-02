import { Component, ViewEncapsulation, OnInit } from "@angular/core";
import { AuthenticationService } from "./authentication/authentication.service";

@Component({
    selector: "l2f-app",
    templateUrl: "app.component.html",
    styleUrls: ["app.component.css"],
    encapsulation: ViewEncapsulation.None
})
export class AppComponent implements OnInit {
    constructor(private authenticationService: AuthenticationService) {}

    async ngOnInit() {
        this.authenticationService.checkCurrentUser();
    }
}