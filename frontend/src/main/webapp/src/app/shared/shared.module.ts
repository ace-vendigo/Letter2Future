import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { RouterModule } from "@angular/router";

import { NavigationComponent } from "./navigation/index";
import { HttpService } from "./services/http.service";

@NgModule({
    imports: [CommonModule, RouterModule],
    declarations: [NavigationComponent],
    providers: [HttpService],
    exports: [NavigationComponent],
})
export class SharedModule {
}