import { NgModule, NgModuleMetadataType } from "@angular/core";
import { CommonModule } from "@angular/common";
import { ReactiveFormsModule } from "@angular/forms";

import { authenticationRouting } from "./authentication.routing"
import { AuthenticationService } from "./authentication.service";

import { RegistrationComponent } from "./registration";
import { LoginComponent } from "./login";

@NgModule(<NgModuleMetadataType>{
    imports: [
        CommonModule,
        ReactiveFormsModule,
        authenticationRouting
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