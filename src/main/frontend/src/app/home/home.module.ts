import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";

import { HomeComponent } from "./home.component";
import { HomeService } from "./home.service";
import { RouterModule } from "@angular/router";
import { homeRoutes } from "./home.routing";


@NgModule({
    imports: [ 
        CommonModule,
        RouterModule.forChild(homeRoutes)
    ],
    declarations: [ 
        HomeComponent
    ],
    providers: [
        HomeService
    ]
})
export class HomeModule {}