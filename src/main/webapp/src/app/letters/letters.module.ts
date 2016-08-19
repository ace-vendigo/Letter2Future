import { NgModule, NgModuleMetadataType } from "@angular/core";
import {CommonModule} from "@angular/common";

import { lettersRouting } from "./letters.routing";
import { LettersComponent } from "./letters.component";

@NgModule(<NgModuleMetadataType>{
    imports: [
        CommonModule,
        lettersRouting
    ],
    declarations: [
        LettersComponent
    ]
})
export class LettersModule {}