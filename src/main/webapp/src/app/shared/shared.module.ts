import {NgModule, NgModuleMetadataType} from "@angular/core";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";

import {NavigationComponent} from "./navigation";

@NgModule(<NgModuleMetadataType>{
    imports: [ CommonModule, RouterModule ],
    declarations: [ NavigationComponent ],
    exports: [ NavigationComponent ],
})
export class SharedModule {}