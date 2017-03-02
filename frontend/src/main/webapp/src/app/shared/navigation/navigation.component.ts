import { Component } from "@angular/core";
import { AuthenticationService } from "../../authentication/index";

@Component({
    selector: "l2f-navigation",
    templateUrl: "navigation.component.html"
})
export class NavigationComponent {
    private activeRoute:string = "home";
    public routes:string[];
    public isAuthorized:boolean = false;

    constructor(private authenticationService:AuthenticationService) {
        this.updateRoutes();

        this.authenticationService.authenticationAction
            .subscribe((isAuthorized:boolean) => {
                this.isAuthorized = isAuthorized;
                this.updateRoutes();
            });
    }

    public clickLink(route:string):void {
        this.activeRoute = route;
    }

    public isActiveRoute(route:string) {
        return this.activeRoute === route;
    }

    private updateRoutes() {
        if (this.isAuthorized) {
            this.routes = ["home", "letters"];
        }
        else {
            this.routes = ["home"];
        }
    }

    public login() {
        // this.authenticationService.login();
    }

    public async logout() {
        await this.authenticationService.logout();
    }
}