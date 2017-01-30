import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { RouterModule } from "@angular/router";

import { NavigationComponent } from "./navigation/index";
import { HttpService } from "./services/http.service";
import { CookieService } from "angular2-cookie/core";

@NgModule({
    imports: [CommonModule, RouterModule],
    declarations: [NavigationComponent],
    providers: [HttpService, CookieService],
    exports: [NavigationComponent],
})
export class SharedModule {
}