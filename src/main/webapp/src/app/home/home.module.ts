import {NgModule, NgModuleMetadataType} from "@angular/core";
import {CommonModule} from "@angular/common";

import { HomeComponent } from "./home.component";
import { homeRouting } from "./home.routing";
import { HomeService } from "./home.service";


@NgModule(<NgModuleMetadataType>{
    imports: [ 
        CommonModule,
        homeRouting
    ],
    declarations: [ 
        HomeComponent
    ],
    providers: [
        HomeService
    ]
})
export class HomeModule {}