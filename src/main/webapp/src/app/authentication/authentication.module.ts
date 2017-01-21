import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { ReactiveFormsModule } from "@angular/forms";

import { authenticationRoutes } from "./authentication.routing"
import { AuthenticationService } from "./authentication.service";

import { RegistrationComponent } from "./registration";
import { LoginComponent } from "./login";
import {RouterModule} from "@angular/router";

@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        RouterModule.forChild(authenticationRoutes)
    ],
    declarations: [
        RegistrationComponent,
        LoginComponent
    ],
    providers: [
        AuthenticationService
    ]
})
export class AuthenticationModule {}