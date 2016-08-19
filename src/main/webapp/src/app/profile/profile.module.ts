import {NgModule, NgModuleMetadataType} from "@angular/core";
import { CommonModule } from "@angular/common";

import { ProfileComponent } from "./profile.component";
import { ProfileService } from "./profile.service";
import { profileRouting } from "./profile.routing";


@NgModule(<NgModuleMetadataType>{
    imports: [
        CommonModule,
        profileRouting
    ],
    declarations: [
        ProfileComponent
    ],
    providers: [
        ProfileService
    ]
})
export class ProfileModule {}