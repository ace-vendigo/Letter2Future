import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { ProfileComponent } from "./profile.component";
import { ProfileService } from "./profile.service";
import { profileRoutes } from "./profile.routing";
import { RouterModule } from "@angular/router";


@NgModule({
    imports: [
        CommonModule,
        RouterModule.forChild(profileRoutes)
    ],
    declarations: [
        ProfileComponent
    ],
    providers: [
        ProfileService
    ]
})
export class ProfileModule {
}