import { NgModule } from "@angular/core";
import {CommonModule} from "@angular/common";

import { lettersRoutes } from "./letters.routing";
import { LettersComponent } from "./letters.component";
import { LetterComposeComponent } from "./letter-compose/letter-compose.component";
import { RouterModule } from "@angular/router";
import { LetterListComponent } from "./letter-list/letter-list.component";

@NgModule({
    imports: [
        CommonModule,
        RouterModule.forChild(lettersRoutes)
    ],
    declarations: [
        LettersComponent,
        LetterComposeComponent,
        LetterListComponent
    ]
})
export class LettersModule {}