import { Routes } from "@angular/router";

import { RegistrationComponent } from "./registration/index";
import {LoginComponent} from "./login/index";

export const authenticationRoutes: Routes = [
    { path: "registration", component: RegistrationComponent},
    { path: "login", component: LoginComponent},
];